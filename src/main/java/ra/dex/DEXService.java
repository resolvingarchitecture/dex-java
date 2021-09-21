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
    public static final String OPERATION_OFFER_MADE = "RESPONSE_OFFERS_LIST";

    // Maker
    public static final String OPERATION_MAKE_OFFER = "MAKE_OFFER";
    public static final String OPERATION_OFFER_ACCEPTED = "OFFER_ACCEPTED";
    public static final String OPERATION_MAKER_ESCROWED = "MAKER_ESCROWED";

    // Taker
    public static final String OPERATION_ACCEPT_OFFER = "ACCEPT_OFFER";
    public static final String OPERATION_ACCEPTANCE_ACK = "ACCEPTANCE_ACK";
    public static final String OPERATION_TAKER_ESCROWED = "TAKER_ESCROWED";

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
            case OPERATION_MAKE_OFFER: { // Maker
                Offer offer = getOffer(e);
                if(offer==null) return;
                // Now Publish Offer
                Envelope request = Envelope.documentFactory();
                request.addNVP(Offer.class.getName(), offer.toMap());
                request.addNVP(NetworkPeer.class.getName(), dexPeers);
                // Send to Network Manager to determine external route
                request.addRoute("ra.networkmanager.NetworkManagerService", "PUBLISH");
                send(request);
                break;
            }
            case OPERATION_OFFER_MADE: { // All DEX Peers

                break;
            }
            case OPERATION_ACCEPT_OFFER: { // Taker
                Offer offer = getOffer(e);
                if(offer==null) return;
                Envelope request = Envelope.documentFactory();

                break;
            }
            case OPERATION_OFFER_ACCEPTED: { // Maker

                break;
            }
            case OPERATION_ACCEPTANCE_ACK: { // Taker

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

    private Offer getOffer(Envelope e) {
        Offer offer = null;
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
        }
        return offer;
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
