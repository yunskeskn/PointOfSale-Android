package com.example.yunus.pointofsale;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Belal on 10/2/2017.
 */

public interface Api {

    //String BASE_URL = "https://posnetict.yapikredi.com.tr/MerchantBE/api/";

    String BASE_URL = "http://www.zehraisilyilmaz.com/MerchantBE/api/";

    @POST("Sale")
    Call<SaleResponse> Sale(@Body SaleRequest saleRequest);
}