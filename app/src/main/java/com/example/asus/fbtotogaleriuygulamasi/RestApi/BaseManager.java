package com.example.asus.fbtotogaleriuygulamasi.RestApi;

public class BaseManager {
    protected RestApi getRestApiClient()
    {
        RestApiClient restApiClient = new RestApiClient(BaseUrl.Url);
        return restApiClient.getRestApi();
    }
}
