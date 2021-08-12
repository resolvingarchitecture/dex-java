package ra.dex;

import ra.common.Envelope;
import ra.common.messaging.MessageProducer;
import ra.common.route.Route;
import ra.common.service.BaseService;
import ra.common.service.ServiceStatusObserver;

import java.util.logging.Logger;

/**
 * Provides a simple Decentralized Exchange for goods.
 */
public class DEXService extends BaseService {

    private static final Logger LOG = Logger.getLogger(DEXService.class.getName());

    public static final String OPERATION_REQUEST_DEX_PEERS_LIST = "REQUEST_DEX_PEERS_LIST";
    public static final String OPERATION_RESPONSE_DEX_PEERS_LIST = "RESPONSE_DEX_PEERS_LIST";
    public static final String OPERATION_REQUEST_OFFERS_LIST = "REQUEST_OFFERS_LIST";
    public static final String OPERATION_RESPONSE_OFFERS_LIST = "RESPONSE_OFFERS_LIST";

    public static final String OPERATION_MAKE_OFFER = "MAKE_OFFER";
    public static final String OPERATION_OFFER_ACCEPTED = "OFFER_ACCEPTED";

    public static final String OPERATION_ACCEPT_OFFER = "ACCEPT_OFFER";
    public static final String OPERATION_ACCEPTANCE_ACCEPTED = "ACCEPTANCE_ACCEPTED";

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

                break;
            }
            case OPERATION_RESPONSE_DEX_PEERS_LIST: {

                break;
            }
            case OPERATION_REQUEST_OFFERS_LIST: {

                break;
            }
            case OPERATION_RESPONSE_OFFERS_LIST: {

                break;
            }
            case OPERATION_MAKE_OFFER: {

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

}
