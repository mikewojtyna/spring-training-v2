package pl.wojtyna.trainings.spring.examples.injection;

public class SingletonServiceUsingRequest {

    private final RequestScopeService requestScopeService;

    public SingletonServiceUsingRequest(RequestScopeService requestScopeService) {
        this.requestScopeService = requestScopeService;
    }

    public RequestScopeService getRequestScopeService() {
        return requestScopeService;
    }

    public String getIdOfRequestBean() {
        return requestScopeService.getId();
    }
}
