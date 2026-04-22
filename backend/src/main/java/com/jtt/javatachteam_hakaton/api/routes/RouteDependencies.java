package com.jtt.javatachteam_hakaton.api.routes;

import com.jtt.javatachteam_hakaton.api.service.StubApiService;

final class RouteDependencies {
    private static final StubApiService STUB_API_SERVICE = new StubApiService();

    private RouteDependencies() {
    }

    static StubApiService stubApiService() {
        return STUB_API_SERVICE;
    }
}
