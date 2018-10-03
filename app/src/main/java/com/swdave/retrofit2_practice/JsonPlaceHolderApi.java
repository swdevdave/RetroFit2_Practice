package com.swdave.retrofit2_practice;


import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {

    // Base Address - Https://jsonplaceholder.typicode.com/

    /************ General Use GET ***************/

    /* -------   Basic Get -------
    // GET  /posts
    @GET("posts")
    Call<List<Post>> getPosts1();
    */

    /* -------- Basic Get with Query  ----------
    // GET  /posts?userId=1
    @GET("posts")
    Call<List<Post>> getPosts2(@Query ("userId") int userId);
    */

    /* --------- Basic Get Using Vars for path ---------------
    //GET posts/1/comments
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    /* --------- Basic Get Adding Multiple Queries  ---------
    // GET  /posts?userId=1&_sort=id&_order=desc
    @GET("posts")
    Call<List<Post>> getPosts2(
            @Query("userId") int userId,
            @Query("_sort")String sort,
            @Query("_order")String order);
    */


    /************** Advanced *************/

    /* ----------  Arrays  ------------------- */

    // GET Array of userId's
    @GET("posts")
    Call<List<Post>> getPosts2(
            @Query("userId")Integer[] userId,
            @Query("_sort")String sort,
            @Query("_order")String order);

    /*
    On the Call we add 3 args (new Integer[]{1,3,5,7},XXX ,XXX )
    Call<List<Post>> call = jsonPlaceHolderApi.getPosts2(new Integer[]{1,3,5,7} ,null, null);

    You can also do @Query("userId")List<Integer> userId

    Var Args @Query("userId")Integer.. userId // but must be last line
    */



    /* ---------  Allow Nulls for Integers - Change Primitive int to Integer ------------

    // GET  /posts?userId=1&_sort=id&_order=desc
    @GET("posts")
    Call<List<Post>> getPosts2(
            @Query("userId")Integer userId,
            @Query("_sort")String sort,
            @Query("_order")String order);
    */

    /* --------  Hash Map approach -----------

    // String = Name of Var
    // String = Declared value

    @GET("posts")
    Call<List<Post>> getPosts2(@QueryMap Map<String, String> parameters);

     // Add to top of Method call
     Map<String, String> parameters = new HashMap<>();
     parameters.put("UserId", "1");
     parameters.put("_sort", "id");
     parameters.put("_order", "desc");

     // pass in parameters
     Call<List<Post>> call = jsonPlaceHolderApi.getPosts2(parameters);

    */

    /* ------- Direct URL for complex end points ---------------

    //GET complex URL endpoints
    @GET
    Call<List<Comment>> getComments(@Url String url);

    // inside method call
    Call<List<Comment>> call = jsonPlaceHolderApi.getPosts2("very/difficult/Url/");

    -- ALTERNATIVELY override full URL --
    Call<List<Comment>> call = jsonPlaceHolderApi.getPosts2("https://swdave.com/very/difficult/Url/");

    -- ALTERNATIVELY Another way --
    @GET("https://swdave.com/very/difficult/Url")
    Call<List<Post>> getPosts2(
            @Query("userId")Integer userId,
            @Query("_sort")String sort,
            @Query("_order")String order);
    */

    /* ---------- Replace Trailing slash ----------
    Example Https://jsonplaceholder.typicode.com/v3/
    adding /posts instead of posts removes the v3/ and produces:

    Https://jsonplaceholder.typicode.com/posts/

    @GET("/posts")
    Call<List<Post>> getPosts1();

     */

}
