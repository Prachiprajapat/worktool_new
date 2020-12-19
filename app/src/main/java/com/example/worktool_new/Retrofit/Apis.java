
package com.example.worktool_new.Retrofit;

import com.example.worktool_new.Models.AcceptInvitationRejectionModel;
import com.example.worktool_new.Models.AddCerModel;
import com.example.worktool_new.Models.AddMemberModel;
import com.example.worktool_new.Models.AdvanceSearchCVModel;
import com.example.worktool_new.Models.ArticleProModel;
import com.example.worktool_new.Models.ArticlesModel;
import com.example.worktool_new.Models.CommentDeleteModel;
import com.example.worktool_new.Models.ComposeMailModel;
import com.example.worktool_new.Models.ConseillerModel;
import com.example.worktool_new.Models.DeleteAgendaModel;
import com.example.worktool_new.Models.DeleteEventModel;
import com.example.worktool_new.Models.DeleteMessageModel;
import com.example.worktool_new.Models.ExcludeInvitationModel;
import com.example.worktool_new.Models.ForwardMailModel;
import com.example.worktool_new.Models.GetCERModel;
import com.example.worktool_new.Models.GetEngagementModel;
import com.example.worktool_new.Models.InvitationRecievedModel;
import com.example.worktool_new.Models.ListEventsModel;
import com.example.worktool_new.Models.LoginModel;
import com.example.worktool_new.Models.MemberChangePasswordModel;
import com.example.worktool_new.Models.MemberModel;
import com.example.worktool_new.Models.MyAgendaModel;
import com.example.worktool_new.Models.MyAlertsModel;
import com.example.worktool_new.Models.MyCVModel;
import com.example.worktool_new.Models.MyDataModel;
import com.example.worktool_new.Models.MyDeleteDataModel;
import com.example.worktool_new.Models.MyEventModel;
import com.example.worktool_new.Models.MyInboxModel;
import com.example.worktool_new.Models.MyNetworkModel;
import com.example.worktool_new.Models.MySentMessageModel;
import com.example.worktool_new.Models.ParticipationListModel;
import com.example.worktool_new.Models.PostProfileModel;
import com.example.worktool_new.Models.PostWallCommentModel;
import com.example.worktool_new.Models.ProfileModel;
import com.example.worktool_new.Models.RegisterEventModel;
import com.example.worktool_new.Models.RenameDataModel;
import com.example.worktool_new.Models.SendInvitationModel;
import com.example.worktool_new.Models.SendNetworkInvitationModel;
import com.example.worktool_new.Models.SignatureUploadModel;
import com.example.worktool_new.Models.StatsModel;
import com.example.worktool_new.Models.ToModel;
import com.example.worktool_new.Models.WallCommentModel;
import com.example.worktool_new.Models.WallModel;
import com.example.worktool_new.Models.WallProModel;
import com.example.worktool_new.Models.WallRemovePostModel;
import com.example.worktool_new.Models.deleteMemberModel.DeleteMemberModel;
import com.example.worktool_new.Models.getskills.SkillModel;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Apis {
    @POST("post_editevent.php")
    @Multipart
    Call<ResponseBody> EditEvent(@Part("id") RequestBody requestBody, @Part("idCompteAuteur") RequestBody requestBody2, @Part("statut") RequestBody requestBody3, @Part("date") RequestBody requestBody4, @Part("resume") RequestBody requestBody5, @Part("titre") RequestBody requestBody6, @Part("rubrique") RequestBody requestBody7, @Part("filesavedid") RequestBody requestBody8, @Part("description") RequestBody requestBody9, @Part MultipartBody.Part part);

    @POST("post_importcvfile.php")
    @Multipart
    Call<ResponseBody> ImportCv(@Part("title") RequestBody requestBody, @Part("summary") RequestBody requestBody2, @Part("id") RequestBody requestBody3,@Part MultipartBody.Part part);

    @POST("post_editevent.php")
    @Multipart
    Call<ResponseBody> EditEventWithoutImage(@Part("id") RequestBody requestBody, @Part("idCompteAuteur") RequestBody requestBody2, @Part("statut") RequestBody requestBody3, @Part("date") RequestBody requestBody4, @Part("resume") RequestBody requestBody5, @Part("titre") RequestBody requestBody6, @Part("rubrique") RequestBody requestBody7, @Part("filesavedid") RequestBody requestBody8, @Part("description") RequestBody requestBody9);

    @FormUrlEncoded
    @POST("post_acceptinvite.php")
    Call<AcceptInvitationRejectionModel> acceptInvite(@Field("idInvite") int i);

    @POST("post_addcer.php")
    Call<AddCerModel> addCerPost(@Body AddCerModel addCerModel);

    @FormUrlEncoded
    @POST("addMember.php")
    Call<AddMemberModel> addMember(@Field("idCompte") String str, @Field("typeM") String str2, @Field("nom") String str3, @Field("prenom") String str4, @Field("civ") String str5, @Field("mail") String str6, @Field("fixe") String str7, @Field("port") String str8, @Field("mdp") String str9, @Field("dateNais") String str10);

    @FormUrlEncoded
    @POST("myCvFilter.php")
    Call<AdvanceSearchCVModel> advanceSearch(@Field("id") int i, @Field("dispo") String str, @Field("titreCv") String str2, @Field("poste") String str3, @Field("competence") String str4, @Field("exp") String str5, @Field("formation") String str6, @Field("permis") String str7, @Field("ville") String str8);

    @GET("article_pro.php")
    Call<ArticleProModel> articlePro(@Query("id") String str);

    @GET("articles.php")
    Call<ArticlesModel> articles(@Query("id") String str);

    @FormUrlEncoded
    @POST("post_wallcommentdelete.php")
    Call<CommentDeleteModel> deleteComment(@Field("idComment") int i);

    @FormUrlEncoded
    @POST("post_deleteevent.php")
    Call<DeleteEventModel> deleteEvent(@Field("idEvent") int i);

    @FormUrlEncoded
    @POST("post_deleteagenda.php")
    Call<DeleteAgendaModel> deleteAgenda(@Field("idEvent") int i);


    @GET("get_deleteMember.php")
    Call<DeleteMemberModel> deleteMember(@Query("idMember") String str);

    @FormUrlEncoded
    @POST("post_deleteMessage.php")
    Call<DeleteMessageModel> deleteMessage(@Field("idMessage") int i, @Field("idCompte") int i2);

    @GET("get_deleteData.php")
    Call<MyDeleteDataModel> deleteMyData(@Query("idData") String str, @Query("table") String str2);

    @FormUrlEncoded
    @POST("post_walldelete.php")
    Call<WallRemovePostModel> deleteWallPost(@Field("idWall") int i);

    @FormUrlEncoded
    @POST("post_wallprodelete.php")
    Call<WallRemovePostModel> deleteWallProPost(@Field("idWall") int i);

    @FormUrlEncoded
    @POST("post_excludeinvitation.php")
    Call<ExcludeInvitationModel> excludeInvitation(@Field("idNetwork") int i);

    @GET("get_cerData.php")
    Call<GetCERModel> getCerData(@Query("idMember") String str);

    @GET("myCV.php")
    Call<MyCVModel> getCvData(@Query("id") String str);

    @GET("get_cerEngagements.php")
    Call<GetEngagementModel> getEngagementData(@Query("idCer") String str);

    @GET("myInvitations.php")
    Call<InvitationRecievedModel> getInvitationRecieved(@Query("id") String str);

    @GET("myMembers.php")
    Call<MemberModel> getMembers(@Query("id") String str);

    @GET("myData.php")
    Call<MyDataModel> getMyData(@Query("id") String str);

    @GET("get_Destinationdata.php")
    Call<ForwardMailModel> get_Destinationdata(@Query("idCompte") String str);

    @GET("myInbox.php")
    Call<MyInboxModel> getMyInbox(@Query("id") String str);

    @GET("mySentMessage.php")
    Call<MySentMessageModel> getMySentMessage(@Query("id") String str);

    @GET("get_participantList.php")
    Call<ParticipationListModel> getParticipationList(@Query("idEvent") int i);

    @GET("myProfile.php")
    Call<ProfileModel> getProfileData(@Query("id") String str);

    @GET("myStat.php")
    Call<StatsModel> getStats(@Query("id") String str);

    @GET("get_Destinationdata.php")
    Call<ToModel> getTo(@Query("idCompte") int i);

    @GET("getWallComment.php")
    Call<WallCommentModel> getWallComments(@Query("idWall") int i);

    @GET("getWallCommentPro.php")
    Call<WallCommentModel> getWallProComments(@Query("idWall") int i);

    @GET("competenceapi.php")
    Call<SkillModel> getskills(@Query("id") String str, @Query("term") String str2);

    @GET("listEvents.php")
    Call<ListEventsModel> listEvents(@Query("id") String str);

    @GET("loginapi.php")
    Call<LoginModel> login(@Query("email") String str, @Query("password") String str2);

    @GET("myAgenda.php")
    Call<MyAgendaModel> myAgenda(@Query("id") String str);

    @GET("myAlerts.php")
    Call<MyAlertsModel> myAlerts(@Query("id") String str);

    @GET("get_conseillerbenef.php")
    Call<ConseillerModel> conseiller(@Query("idCompte") String str);


    @GET("myevents.php")
    Call<MyEventModel> myEvents(@Query("id") String str);

    @GET("myNetwork.php")
    Call<MyNetworkModel> myNetwork(@Query("id") String str);

    @FormUrlEncoded
    @POST("post_wallcomment.php")
    Call<PostWallCommentModel> postComment(@Field("idCompte") int i, @Field("idMur") int i2, @Field("message") String str);

    @POST("postevent.php")
    @Multipart
    Call<PostProfileModel> postEvent(@Part("id") RequestBody requestBody, @Part("evenement") RequestBody requestBody2, @Part("statut") RequestBody requestBody3, @Part("date") RequestBody requestBody4, @Part("heure") RequestBody requestBody5, @Part("event_type") RequestBody requestBody6, @Part("resume") RequestBody requestBody7, @Part("description") RequestBody requestBody8, @Part MultipartBody.Part part, @Part MultipartBody.Part part2);

    @POST("post_inboxMessage.php")
    @Multipart
    Call<ComposeMailModel> postMail(@Part("senderid") RequestBody requestBody, @Part("destination") RequestBody requestBody2, @Part("subject") RequestBody requestBody3, @Part("message") RequestBody requestBody4, @Part MultipartBody.Part part);

    @POST("post_editprofile.php")
    @Multipart
    Call<PostProfileModel> postProfileData(@Part("idCompte") RequestBody requestBody, @Part("desc") RequestBody requestBody2, @Part("ville") RequestBody requestBody3, @Part("cp") RequestBody requestBody4, @Part("adresse") RequestBody requestBody5, @Part("fixe") RequestBody requestBody6, @Part("port") RequestBody requestBody7, @Part("dateNais") RequestBody requestBody8, @Part("civ") RequestBody requestBody9, @Part("nom") RequestBody requestBody10, @Part("prenom") RequestBody requestBody11, @Part("mail") RequestBody requestBody12, @Part("pass") RequestBody requestBody13, @Part("passcconf") RequestBody requestBody14, @Part("imgsel") RequestBody requestBody15, @Part MultipartBody.Part part);

    @POST("post_sentmessage.php")
    @Multipart
    Call<ComposeMailModel> postSentMail(@Part("senderid") RequestBody requestBody, @Part("destination") RequestBody requestBody2, @Part("subject") RequestBody requestBody3, @Part("message") RequestBody requestBody4, @Part("fichier1") RequestBody requestBody5);

    @POST("post_cersignature.php")
    @Multipart
    Call<SignatureUploadModel> postSignature(@Part("idMember") RequestBody requestBody, @Part("type_signature") RequestBody requestBody2, @Part MultipartBody.Part part);

    @POST("postwall.php")
    @Multipart
    Call<DeleteEventModel> postwall(@Part("idCompte") RequestBody requestBody, @Part("wallmessage") RequestBody requestBody2, @Part("email") RequestBody requestBody3, @Part("title") RequestBody requestBody4, @Part MultipartBody.Part part);

    @POST("postwallpro.php")
    @Multipart
    Call<DeleteEventModel> postwallpro(@Part("idCompte") RequestBody requestBody, @Part("wallmessage") RequestBody requestBody2, @Part MultipartBody.Part part);

    @FormUrlEncoded
    @POST("post_refuseinvite.php")
    Call<AcceptInvitationRejectionModel> rejectInvite(@Field("idInvite") int i);
    @FormUrlEncoded
    @POST("post_subscribeEvent.php")
    Call<ResponseBody> registerEvent(@Field("id") String i, @Field("idEvent") String idEvent);

    @FormUrlEncoded
    @POST("MyData_Rename.php")
    Call<RenameDataModel> renameMyData(@Field("idData") int i, @Field("fichier") String str, @Field("table") String str2);

    @FormUrlEncoded
    @POST("post_ModifyPassword.php")
    Call<MemberChangePasswordModel> resetPassword(@Field("idCompte") int i, @Field("password") String str, @Field("idmodif") String str2);

    @FormUrlEncoded
    @POST("getInvitation.php")
    Call<SendInvitationModel> searchInvitationMember(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("post_invite.php")
    Call<SendNetworkInvitationModel> sendInvite(@Field("idToInvite") int i, @Field("idCompte") int i2);

    @GET("wall.php")
    Call<WallModel> wallPosts(@Query("id") String str);

    @GET("wallpro.php")
    Call<WallProModel> wallPro(@Query("id") String str);
}