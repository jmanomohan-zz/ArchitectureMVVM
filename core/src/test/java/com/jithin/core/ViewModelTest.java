package com.jithin.core;

import com.jithin.core.model.Sources;
import com.jithin.core.network.Connector;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Jithin on 05/10/17.
 */

public class ViewModelTest {

    Response<Sources> response_;
    private CountDownLatch lock = new CountDownLatch(1);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetSomeData_emitsCorrectData() throws InterruptedException {
        Call<Sources> call = Connector.load().call().getSources();
        call.enqueue(new Callback<Sources>() {
            @Override
            public void onResponse(Call<Sources> call, Response<Sources> response) {
                response_ = response;
            }

            @Override
            public void onFailure(Call<Sources> call, Throwable t) {
            }
        });
        lock.await(5000, TimeUnit.MILLISECONDS);
        assertEquals(response_.code(), 200);
        System.out.printf("code = "+response_.code());
        assertTrue(response_.body().getSources().size() > 0);
        assertTrue(response_.body().getSources().get(0).getId()!=null);
        System.out.printf("id = "+response_.body().getSources().get(0).getId());
    }
}
