package ra.dex;

import ra.common.Envelope;
import ra.common.messaging.MessageProducer;
import ra.common.network.NetworkPeer;
import ra.common.route.Route;
import ra.common.service.BaseService;
import ra.common.service.Service;
import ra.common.service.ServiceStatus;
import ra.common.service.ServiceStatusObserver;

import java.util.*;
import java.util.logging.Logger;

/**
 * Provides a simple Decentralized Exchange for goods.
 */
public class DEXService extends BaseService {

    private static final Logger LOG = Logger.getLogger(DEXService.class.getName());

    // Request Network Peers running DEX Service
    public static final String OPERATION_REQUEST_DEX_PEERS_LIST = "REQUEST_DEX_PEERS_LIST";
    public static final String OPERATION_RESPONSE_DEX_PEERS_LIST = "RESPONSE_DEX_PEERS_LIST";
    public static final String OPERATION_REQUEST_OFFERS_LIST = "REQUEST_OFFERS_LIST";
    public static final String OPERATION_RESPONSE_OFFERS_LIST = "RESPONSE_OFFERS_LIST";

    public static final String OPERATION_MAKE_OFFER = "MAKE_OFFER";
    public static final String OPERATION_OFFER_ACCEPTED = "OFFER_ACCEPTED";

    public static final String OPERATION_ACCEPT_OFFER = "ACCEPT_OFFER";
    public static final String OPERATION_ACCEPTANCE_ACCEPTED = "ACCEPTANCE_ACCEPTED";

    private List<NetworkPeer> dexPeers;
    private List<Offer> localOffers = new ArrayList<>();
    private final Map<Good,Offer> scopedOffers = new HashMap<>();

    public DEXService() {
    }

    public DEXService(MessageProducer producer, ServiceStatusObserver observer) {
        super(producer, observer);
    }

    @Override
    public void handleDocument(Envelope e) {
        Route route = e.getRoute();
        String operation = route.getOperation();
        switch(operation) {
            case OPERATION_REQUEST_DEX_PEERS_LIST: {
                updateDEXPeersList();
                break;
            }
            case OPERATION_RESPONSE_DEX_PEERS_LIST: {
                dexPeers = (List<NetworkPeer>)e.getValue(NetworkPeer.class.getName());
                break;
            }
            case OPERATION_MAKE_OFFER: {
                Offer offer;
                Object offerObj = e.getValue(Offer.class.getName());
                if(offerObj instanceof Map) {
                    offer = new Offer();
                    offer.fromMap((Map<String,Object>)offerObj);
                } else if(offerObj instanceof Offer) {
                    offer = (Offer)offerObj;
                } else if(offerObj instanceof String) {
                    offer = new Offer();
                    offer.fromJSON((String)offerObj);
                } else {
                    LOG.warning("Unable to determine Offer.");
                    e.addErrorMessage("Unable to determine Offer.");
                    deadLetter(e);
                    return;
                }
                // Now Publish Offer
                for(NetworkPeer dp : dexPeers) {
                    Envelope request = Envelope.documentFactory();
                    request.addNVP(Service.class.getName(), DEXService.class.getName());
                    // Send 3rd back here so we can pull update the list of offers
                    request.addRoute(DEXService.class.getName(), OPERATION_RESPONSE_OFFERS_LIST);
                    // Send 2nd to I2P if available
                    request.addRoute("ra.i2p.I2PService", "SEND");
                    // Send 1st to Network Manager asking to forward to I2P
                    request.addRoute("ra.networkmanager.NetworkManagerService", "SEND");
                    send(request);
                }
                break;
            }
            case OPERATION_OFFER_ACCEPTED: {

                break;
            }
            case OPERATION_ACCEPT_OFFER: {

                break;
            }
            case OPERATION_ACCEPTANCE_ACCEPTED: {

                break;
            }
            default:
                deadLetter(e); // Operation not supported
        }
    }

    private void updateDEXPeersList() {
        Envelope request = Envelope.documentFactory();
        request.addNVP(Service.class.getName(), DEXService.class.getName());
        request.addRoute(DEXService.class.getName(), OPERATION_RESPONSE_DEX_PEERS_LIST);
        request.addRoute("ra.networkmanager.NetworkManagerService", "PEERS_BY_SERVICE");
        send(request);
    }

    @Override
    public boolean start(Properties p) {
        LOG.info("Starting...");
        updateStatus(ServiceStatus.STARTING);
        if(!super.start(p))
            return false;
        LOG.info("Loading properties...");

        updateStatus(ServiceStatus.RUNNING);
        LOG.info("Started.");
        return true;
    }

    @Override
    public boolean shutdown() {
        LOG.info("Shutting down...");
        updateStatus(ServiceStatus.SHUTTING_DOWN);


        updateStatus(ServiceStatus.SHUTDOWN);
        LOG.info("Shutdown.");
        return true;
    }

    @Override
    public boolean gracefulShutdown() {
        LOG.info("Gracefully shutting down...");
        updateStatus(ServiceStatus.GRACEFULLY_SHUTTING_DOWN);


        updateStatus(ServiceStatus.GRACEFULLY_SHUTDOWN);
        LOG.info("Gracefully shutdown.");
        return true;
    }
}
