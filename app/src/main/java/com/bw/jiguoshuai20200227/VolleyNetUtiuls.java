package com.bw.jiguoshuai20200227;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyNetUtiuls {

    RequestQueue requestQueue;

    public VolleyNetUtiuls() {
        this.requestQueue = Volley.newRequestQueue(App.getApplicon());
        RequestQueue requestQueue = this.requestQueue;
    }
    private static class SingInstance{
        private static final VolleyNetUtiuls IBNER=new VolleyNetUtiuls();
    }
    public static VolleyNetUtiuls getInstance(){
        return SingInstance.IBNER;
    }
    public void doGet(String url, final ICallback iCallback){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iCallback.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCallback.onError(error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }
    public interface ICallback{
        void onSuccess(String url);
        void onError(String url);
    }
}
