package com.example.surataccount.ApiInterface;

import com.example.surataccount.AboutPack.AboutModelClass;
import com.example.surataccount.BookSchedule.BookScheduUodateBookingNumber;
import com.example.surataccount.BookSchedule.BookScheduleModelClass;
import com.example.surataccount.BookSchedule.UpdateBookingNumberModel;
import com.example.surataccount.BuyOfferPhotoCover.PhotoCoverTable;
import com.example.surataccount.BuyOfferPhotoCover.PhotoTypeTable;
import com.example.surataccount.CoupanPack.CoupanPageModel;
import com.example.surataccount.CoupanPack.CoupanSummaryModel;


import com.example.surataccount.FAQ.ChattingSelectModel;
import com.example.surataccount.FAQ.FAQInertDataModel;
import com.example.surataccount.FAQ.FAQMainChattingModel;
import com.example.surataccount.LastedDesign.DemoDesignModel;
import com.example.surataccount.Photobook_Status.PhotobookSearchingDateGetterSeter;
import com.example.surataccount.Photobook_Status.Photobook_Gette_Pojo_Class;
import com.example.surataccount.PriceList.PriceListModelClass;
import com.example.surataccount.party.PartyGetterSetterClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaceClass {

    @GET("surataccount/api/phototype")
    Call<PhotoTypeTable> getPhotoTypePhotoCoverImage();

    @GET("surataccount/api/allcoverphoto")
    Call<PhotoCoverTable> getPhotoCover(@Query("Photo_id") String Photo_id);

    @GET("surataccount/api/ledgersummary")
    Call<PartyGetterSetterClass> getLederSmmyser(@Query("mobileno") String mobileno);
    @GET("dmss/api/ScheduleDate")
    Call<BookScheduleModelClass> getBookScheduleData(@Query("sch_date") String sch_date, @Query("designer_id") String designer_id);
    @GET("dmss/api/ScheduleDateSelect")
    Call<UpdateBookingNumberModel> getBookingNumber(@Query("StudioCode") String StudioCode);
    @GET("dmss/api/ScheduleDateUpdate")
    Call<BookScheduUodateBookingNumber> getBookingUpdateNumber(@Query("sch_date") String sch_date, @Query("designer_id") String designer_id,
                                                               @Query("sch_start_time") String sch_start_time,
                                                               @Query("booking_no") String booking_no);

    //coupan
    @GET("surataccount/api/offercoupon")
    Call<CoupanPageModel> getCoupanData(@Query("Coup_SchemesCode") String Coup_SchemesCode,@Query("std_mobile") String std_mobile,
                                        @Query("couponstatus") int couponstatus);
    @GET("surataccount/api/CouponSummary")
    Call<CoupanSummaryModel> getCoupanSummarydata(@Query("mobileno") String mobileno);
    @GET("account/api/Chatting")
    Call<FAQInertDataModel> getFaqdata(@Query("ch_mobile_no") String ch_mobile_no,
                                       @Query("txt_msg") String txt_msg,
                                       @Query("ticket_no") String ticket_no,
                                       @Query("msg_form") String msg_form);
    @GET("account/api/ChattingSelecting")
    Call<ChattingSelectModel> getChattSelectData(@Query("ch_mobile_no") String ch_mobile_no,@Query("msg_form") String msg_form);
    @GET("account/api/ChattingSelectingWithTicket")
    Call<FAQMainChattingModel> getChattSelectWithTicket(@Query("ch_mobile_no") String ch_mobile_no, @Query("msg_form") String msg_form,@Query("ticket_no") String ticket_no);
    //lasted design
    @GET("surataccount/api/DemoAlbum")
    Call<DemoDesignModel> getDemoDesign();
    @GET("surataccount/api/DemoAlbum")
    Call<DemoDesignModel> getDemoAlbumData(@Query("AlbumId") String AlbumId);
    //price list
    @GET("surataccount/api/pricelist")
    Call<PriceListModelClass> getPriceListData(@Query("catgoues_name") String catgoues_name);
    //about page
    @GET("surataccount/api/AboutPage")
    Call<AboutModelClass> getTherory();
    //photobook sattus
    @GET("surataccount/api/pendingbooking")
    Call<Photobook_Gette_Pojo_Class> getPhotoBookStatus(@Query("mobileno") String mobileno);
    @GET("surataccount/api/bookingSearching")
    Call<PhotobookSearchingDateGetterSeter> getPhotoBookStatusFormDate(@Query("formdate") String formdate, @Query("todate") String todate, @Query("StCode") String StCode );
}
