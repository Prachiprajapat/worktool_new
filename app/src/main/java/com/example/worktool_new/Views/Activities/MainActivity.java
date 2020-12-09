package com.example.worktool_new.Views.Activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.worktool_new.R;
import com.example.worktool_new.Util.SharedPreference.App;
import com.example.worktool_new.Util.SharedPreference.AppConstants;
import com.example.worktool_new.Views.Activities.addCer.AddCer;
import com.example.worktool_new.Views.Fragments.Alert;
import com.example.worktool_new.Views.Fragments.Articles;
import com.example.worktool_new.Views.Fragments.Conseiller;
import com.example.worktool_new.Views.Fragments.fragment_Events;
import com.example.worktool_new.Views.Fragments.Inbox;
import com.example.worktool_new.Views.Fragments.Members;
import com.example.worktool_new.Views.Fragments.MyAdvisor;
import com.example.worktool_new.Views.Fragments.MyAgenda;
import com.example.worktool_new.Views.Fragments.MyDataFragment;
import com.example.worktool_new.Views.Fragments.Network;
import com.example.worktool_new.Views.Fragments.Profile;
import com.example.worktool_new.Views.Fragments.Search_Cv;
import com.example.worktool_new.Views.Fragments.Stats;
import com.example.worktool_new.Views.Fragments.Wall;
import com.example.worktool_new.Views.Fragments.WallPro;
import com.google.android.material.navigation.NavigationView;
import com.infideap.drawerbehavior.AdvanceDrawerLayout;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.Calendar;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    CircleImageView ProfileImageView;
    LinearLayout add_event;
    ImageView chat;
    /* access modifiers changed from: private */
    public AdvanceDrawerLayout drawer;
    RelativeLayout headerProfile;
    ImageView iv_alert;
    ImageView iv_cv;
    ImageView iv_mail;
    ImageView iv_member;
    ImageView iv_network;
    ImageView iv_wall;
    LinearLayout ll_alert;
    LinearLayout ll_cv;
    LinearLayout ll_mail;
    LinearLayout ll_members;
    LinearLayout ll_network;
    LinearLayout ll_wall;
    LinearLayout ll_wallpro;
    NavigationView nav_view;
    ImageView side_navigation;
    TextView toolbarText;
    TextView tvAge;
    TextView tvUserName;
    TextView tv_alert;
    TextView tv_cv;
    TextView tv_mail;
    TextView tv_member;
    TextView tv_network;
    TextView tv_wall;

    Intent intent;
    String added="added",event="added";
    String added1,event1;
    Members members=new Members();
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        intent = getIntent();
        added1 = intent.getStringExtra("member");
        event1=intent.getStringExtra("event");

       /* if(added1!=null) {
            if (added.equals(added1)) {
                this.toolbarText.setText("MY MEMBERS");
                this.add_event.setVisibility(8);
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_blue);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.colorPrimary));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
                Log.e("Main init if", "" + added);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, members);
                fragmentTransaction.commit();

                // replaceFragment(new Members(), getResources().getString(R.string.members));

            }
        }
        else {

        }*/
        init();
        String UserType = App.getAppPreference().getString("type");
        if (event1 != null) {
            if (event.equals(event1)) {

                this.toolbarText.setText("MY EVENTS");
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                this.drawer.closeDrawers();
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
                this.chat.setVisibility(8);
                this.add_event.setVisibility(0);
                replaceFragment(new fragment_Events(), getResources().getString(R.string.events));
            }}
        else if (added1 != null) {
            if (added.equals(added1)) {
                Bundle bundle = new Bundle();
                bundle.putString("added", "added");
                members.setArguments(bundle);
                FragmentManager fm = getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, members);
                fragmentTransaction.commit();
                this.add_event.setVisibility(8);
                //replaceFragment(new Members(), getResources().getString(R.string.members));
                this.toolbarText.setText("MY MEMBERS");
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_blue);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.colorPrimary));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
            }
        } else {
            if (UserType.equalsIgnoreCase("counselor")) {
                this.toolbarText.setText("Wall");
                this.ll_members.setVisibility(0);
                this.ll_cv.setVisibility(0);
                this.ll_network.setVisibility(0);
                this.ll_wall.setVisibility(0);
                this.ll_mail.setVisibility(0);
                this.ll_alert.setVisibility(0);
                MenuItem menu_nav_agenda = this.nav_view.getMenu().findItem(R.id.nav_agenda);
                menu_nav_agenda.setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_profile).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_wall).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_cv).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_wallpro).setVisible(true);
                //this.nav_view.getMenu().findItem(R.id.nav_conseiller).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_network).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_members).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_mail).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_article).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_data).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_event).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_chat).setVisible(false);
                Intent intent2 = intent;
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_blue);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                MenuItem menuItem = menu_nav_agenda;
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.colorPrimary));
                replaceFragment(new Wall(), getResources().getString(R.string.wall));
                return;
            }
            if (UserType.equalsIgnoreCase("benificiary") || UserType.equalsIgnoreCase("jeune")) {
                this.toolbarText.setText("Wall");
                this.ll_members.setVisibility(8);
                this.ll_cv.setVisibility(0);
                this.ll_network.setVisibility(0);
                this.ll_wall.setVisibility(0);
                this.ll_mail.setVisibility(0);
                this.ll_alert.setVisibility(0);
                MenuItem menu_nav_agenda2 = this.nav_view.getMenu().findItem(R.id.nav_agenda);
                menu_nav_agenda2.setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_profile).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_wall).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_agenda).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_cv).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_wallpro).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_network).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_members).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_mail).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_article).setVisible(true);
                //conseiler addition
                this.nav_view.getMenu().findItem(R.id.nav_counselor).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_data).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_event).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_chat).setVisible(false);
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_blue);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                MenuItem menuItem2 = menu_nav_agenda2;
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.colorPrimary));
                replaceFragment(new Wall(), getResources().getString(R.string.wall));
            } else if (UserType.equalsIgnoreCase("referent")) {
                this.toolbarText.setText("Wall");
                this.ll_members.setVisibility(0);
                this.ll_cv.setVisibility(0);
                this.ll_network.setVisibility(0);
                this.ll_wall.setVisibility(0);
                this.ll_mail.setVisibility(0);
                this.ll_alert.setVisibility(0);
                MenuItem menu_nav_agenda3 = this.nav_view.getMenu().findItem(R.id.nav_agenda);
                menu_nav_agenda3.setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_profile).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_wall).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_cv).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_wallpro).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_network).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_counselor).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_agenda).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_members).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_mail).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_article).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_data).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_event).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_chat).setVisible(false);
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_blue);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                MenuItem menuItem3 = menu_nav_agenda3;
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.colorPrimary));
                replaceFragment(new Wall(), getResources().getString(R.string.wall));
            } else if (UserType.equalsIgnoreCase("godfather")) {
                this.toolbarText.setText("Wall");
                this.ll_members.setVisibility(8);
                this.ll_cv.setVisibility(0);
                this.ll_network.setVisibility(0);
                this.ll_wall.setVisibility(0);
                this.ll_mail.setVisibility(0);
                this.ll_alert.setVisibility(0);
                MenuItem menu_nav_agenda4 = this.nav_view.getMenu().findItem(R.id.nav_agenda);
                menu_nav_agenda4.setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_profile).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_wall).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_agenda).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_cv).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_counselor).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_wallpro).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_network).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_members).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_mail).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_article).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_data).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_event).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_chat).setVisible(false);
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_blue);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                MenuItem menuItem4 = menu_nav_agenda4;
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.colorPrimary));
                replaceFragment(new Wall(), getResources().getString(R.string.wall));
            } else if (UserType.equalsIgnoreCase("professionel")) {
                this.toolbarText.setText("Wall");
                this.ll_members.setVisibility(8);
                this.ll_cv.setVisibility(0);
                this.ll_network.setVisibility(8);
                this.ll_wall.setVisibility(8);
                this.ll_mail.setVisibility(0);
                this.ll_alert.setVisibility(0);
                MenuItem menu_nav_agenda5 = this.nav_view.getMenu().findItem(R.id.nav_agenda);
                menu_nav_agenda5.setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_profile).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_wall).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_cv).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_wallpro).setVisible(false);
                //conseilier addition
                this.nav_view.getMenu().findItem(R.id.nav_counselor).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_network).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_members).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_mail).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_article).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_data).setVisible(true);
                this.nav_view.getMenu().findItem(R.id.nav_event).setVisible(false);
                this.nav_view.getMenu().findItem(R.id.nav_chat).setVisible(false);
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_blue);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                MenuItem menuItem5 = menu_nav_agenda5;
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.colorPrimary));
                replaceFragment(new Wall(), getResources().getString(R.string.wall));
            } else {
                replaceFragment(new Members(), getResources().getString(R.string.members));
                this.add_event.setVisibility(8);
                this.toolbarText.setText("MY MEMBERS");
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_blue);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.colorPrimary));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
            }
        }
    }

    private void movetoMembers() {
        this.add_event.setVisibility(8);
        replaceFragment(new Members(), getResources().getString(R.string.members));
        this.toolbarText.setText("MY MEMBERS");
        this.iv_network.setImageResource(R.drawable.network_grey);
        this.iv_member.setImageResource(R.drawable.members_blue);
        this.iv_alert.setImageResource(R.drawable.alert_grey);
        this.iv_cv.setImageResource(R.drawable.cv_grey);
        this.iv_wall.setImageResource(R.drawable.wall_grey);
        this.iv_mail.setImageResource(R.drawable.mail_grey);
        this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
        this.tv_member.setTextColor(getResources().getColor(R.color.colorPrimary));
        this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
        this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
        this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
        this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
    }

    public void init() {
        this.ll_members = (LinearLayout) findViewById(R.id.ll_members);
        this.ll_cv = (LinearLayout) findViewById(R.id.ll_cv);
        this.ll_network = (LinearLayout) findViewById(R.id.ll_network);
        this.ll_wall = (LinearLayout) findViewById(R.id.ll_wall);
        this.ll_wallpro = (LinearLayout) findViewById(R.id.ll_wallpro);
        this.ll_alert = (LinearLayout) findViewById(R.id.ll_alert);
        this.ll_mail = (LinearLayout) findViewById(R.id.ll_mail);
        this.iv_member = (ImageView) findViewById(R.id.iv_member);
        this.iv_cv = (ImageView) findViewById(R.id.iv_cv);
        this.iv_network = (ImageView) findViewById(R.id.iv_network);
        this.iv_wall = (ImageView) findViewById(R.id.iv_wall);
        this.iv_mail = (ImageView) findViewById(R.id.iv_mail);
        this.iv_alert = (ImageView) findViewById(R.id.iv_alert);
        this.tv_member = (TextView) findViewById(R.id.tv_member);
        this.tv_cv = (TextView) findViewById(R.id.tv_cv);
        this.tv_network = (TextView) findViewById(R.id.tv_network);
        this.tv_wall = (TextView) findViewById(R.id.tv_wall);
        this.tv_mail = (TextView) findViewById(R.id.tv_mail);
        this.tv_alert = (TextView) findViewById(R.id.tv_alert);
        this.add_event = (LinearLayout) findViewById(R.id.add_event);
        this.chat = (ImageView) findViewById(R.id.chat);
        this.toolbarText = (TextView) findViewById(R.id.toolbarText);
        this.side_navigation = (ImageView) findViewById(R.id.side_navigation);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.nav_view = navigationView;
        navigationView.setItemIconTintList((ColorStateList) null);
        View hView = this.nav_view.getHeaderView(0);
        this.ProfileImageView = (CircleImageView) hView.findViewById(R.id.ProfileImageView);
        this.tvAge = (TextView) hView.findViewById(R.id.tvAge);
        this.tvUserName = (TextView) hView.findViewById(R.id.tvUserName);
        AdvanceDrawerLayout advanceDrawerLayout = (AdvanceDrawerLayout) findViewById(R.id.drawer);
        this.drawer = advanceDrawerLayout;
        advanceDrawerLayout.setRadius(GravityCompat.START, 25.0f);
        this.drawer.setViewScale(GravityCompat.START, 0.9f);
        this.drawer.setViewElevation(GravityCompat.START, 20.0f);
        Log.e("Main init",""+added);
       /* if(added1!=null) {
            Log.e("AfterNullIf",""+added);
            if (added.equals(added1)) {
                this.add_event.setVisibility(8);
                Log.e("After equal if",""+added);
                replaceFragment(new Members(), getResources().getString(R.string.members));
                this.toolbarText.setText("MY MEMBERS");

                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_blue);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.colorPrimary));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
                Log.e("Main init if", "" + added);
               *//* FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.container, members);
                fragmentTransaction.commit();*//*



            }
        }*/


        setupListners();
        /*Intent intent=getIntent();
        String added=intent.getStringExtra("member");*/

        String ProfileImage = AppConstants.IMAGEURL + App.getAppPreference().getString("profileImage");
        if (ProfileImage != null) {
            Picasso.get().load(ProfileImage).placeholder((int) R.drawable.profileplaceholder).error((int) R.drawable.profileplaceholder).into((ImageView) this.ProfileImageView);
        } else {
            this.ProfileImageView.setImageResource(R.drawable.profileplaceholder);
        }
        this.tvUserName.setText(App.getAppPreference().getString("Name"));
        String DOB = App.getAppPreference().getString("dob");
        if (DOB != null) {
            String[] words = DOB.split("-");
            getAge(parseInt(words[0]), parseInt(words[1]), parseInt(words[2]));
        }
        this.ProfileImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.replaceFragment(new Profile(), MainActivity.this.getResources().getString(R.string.profile));
                MainActivity.this.toolbarText.setText("MY PROFILE");
                MainActivity.this.iv_network.setImageResource(R.drawable.network_grey);
                MainActivity.this.iv_member.setImageResource(R.drawable.members_grey);
                MainActivity.this.iv_alert.setImageResource(R.drawable.alert_grey);
                MainActivity.this.iv_cv.setImageResource(R.drawable.cv_grey);
                MainActivity.this.iv_wall.setImageResource(R.drawable.wall_grey);
                MainActivity.this.iv_mail.setImageResource(R.drawable.mail_grey);
                MainActivity.this.drawer.closeDrawers();
                MainActivity.this.tv_network.setTextColor(MainActivity.this.getResources().getColor(R.color.textGrey));
                MainActivity.this.tv_member.setTextColor(MainActivity.this.getResources().getColor(R.color.textGrey));
                MainActivity.this.tv_cv.setTextColor(MainActivity.this.getResources().getColor(R.color.textGrey));
                MainActivity.this.tv_alert.setTextColor(MainActivity.this.getResources().getColor(R.color.textGrey));
                MainActivity.this.tv_mail.setTextColor(MainActivity.this.getResources().getColor(R.color.textGrey));
                MainActivity.this.tv_wall.setTextColor(MainActivity.this.getResources().getColor(R.color.textGrey));
            }
        });
    }

    public void setupListners() {
        this.ll_members.setOnClickListener(this);
        this.ll_cv.setOnClickListener(this);
        this.ll_network.setOnClickListener(this);
        this.ll_wall.setOnClickListener(this);
        this.ll_wallpro.setOnClickListener(this);
        this.ll_alert.setOnClickListener(this);
        this.ll_mail.setOnClickListener(this);
        this.add_event.setOnClickListener(this);
        this.side_navigation.setOnClickListener(this);
        this.nav_view.setNavigationItemSelectedListener(this);
    }

    public void getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.set(year, month - 1, day);
        int age = today.get(1) - dob.get(1);
        if (today.get(6) < dob.get(6)) {
            age--;
        }
        String ageS = Integer.toString(age);
        this.tvAge.setText(ageS + " Years old");
    }

    public void onClick(View v) {
        if (v == this.ll_members) {
            this.add_event.setVisibility(8);
            replaceFragment(new Members(), getResources().getString(R.string.members));
            this.toolbarText.setText("MY MEMBERS");
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_blue);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_grey);
            this.iv_mail.setImageResource(R.drawable.mail_grey);
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.colorPrimary));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
        } else if (v == this.ll_network) {
            this.add_event.setVisibility(8);
            replaceFragment(new Network(), getResources().getString(R.string.network));
            this.toolbarText.setText("MY NETWORK");
            this.iv_network.setImageResource(R.drawable.network_blue);
            this.iv_member.setImageResource(R.drawable.members_grey);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_grey);
            this.iv_mail.setImageResource(R.drawable.mail_grey);
            this.tv_network.setTextColor(getResources().getColor(R.color.colorPrimary));
            this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
        } else if (v == this.ll_wall) {
            this.add_event.setVisibility(8);
            Intent intent = getIntent();
            if (Objects.equals(intent.getStringExtra("referent"), "referent")) {
                this.toolbarText.setText("PRO");
            } else if (Objects.equals(intent.getStringExtra("godfather"), "godfather")) {
                this.toolbarText.setText("PRO");
            } else if (Objects.equals(intent.getStringExtra("professionel"), "professionel")) {
                this.toolbarText.setText("PRO");
            } else {
                this.toolbarText.setText("WALL");
            }
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_grey);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_blue);
            this.iv_mail.setImageResource(R.drawable.mail_grey);
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_wall.setTextColor(getResources().getColor(R.color.colorPrimary));
            replaceFragment(new Wall(), getResources().getString(R.string.wall));
        } else {
            LinearLayout linearLayout = this.add_event;
            if (v == linearLayout) {
                this.toolbarText.setText("EVENTS");
                App.getAppPreference().saveString("eventscreen", "add");
                startActivity(new Intent(this, Add_Event.class));
            } else if (v == this.ll_cv) {
                linearLayout.setVisibility(8);
                this.toolbarText.setText("SEARCH CV");
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_blue);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.colorPrimary));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
                replaceFragment(new Search_Cv(), getResources().getString(R.string.searchcv));
            } else if (v == this.ll_mail) {
                linearLayout.setVisibility(8);
                this.toolbarText.setText("INBOX");
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_blue);
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.colorPrimary));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
                replaceFragment(new Inbox(), getResources().getString(R.string.inbox));
            } else if (v == this.ll_alert) {
                linearLayout.setVisibility(8);
                this.toolbarText.setText("MY ALERTS");
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_blue);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.colorPrimary));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
                replaceFragment(new Alert(), getResources().getString(R.string.alert));
            } else if (v == this.side_navigation) {
                this.drawer.openDrawer((int) GravityCompat.START);
            }
        }
    }

    public void replaceFragment(Fragment frag, String TAG) {
        FragmentManager manager = getSupportFragmentManager();
        if (manager != null) {
            FragmentTransaction t = manager.beginTransaction();
            Fragment currentFrag = manager.findFragmentByTag(TAG);
            if (currentFrag != null && currentFrag.getClass().equals(frag.getClass())) {
                t.replace(R.id.container, frag).commit();
            } else if (!TAG.equals(getResources().getString(R.string.members))) {
                t.replace(R.id.container, frag, TAG).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack((String) null).commit();
            } else {
                t.replace(R.id.container, frag, TAG).commit();
            }
        }
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_members) {
            this.toolbarText.setText("MY MEMBERS");
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_blue);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_grey);
            this.iv_mail.setImageResource(R.drawable.mail_grey);
            this.drawer.closeDrawers();
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.colorPrimary));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
            replaceFragment(new Members(), getResources().getString(R.string.members));
            return true;
        } else if (id == R.id.ProfileImageView) {
            replaceFragment(new Profile(), getResources().getString(R.string.profile));
            this.toolbarText.setText("MY PROFILE");
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_grey);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_grey);
            this.iv_mail.setImageResource(R.drawable.mail_grey);
            this.drawer.closeDrawers();
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
            return true;
        } else if (id == R.id.nav_advisor) {
            this.toolbarText.setText("MY ADVISOR");
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_grey);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_grey);
            this.iv_mail.setImageResource(R.drawable.mail_grey);
            this.drawer.closeDrawers();
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
            replaceFragment(new MyAdvisor(), getResources().getString(R.string.advisor));
            return true;
        } else if (id == R.id.nav_alert) {
            this.toolbarText.setText("MY ALERTS");
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_grey);
            this.iv_alert.setImageResource(R.drawable.alert_blue);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_grey);
            this.iv_mail.setImageResource(R.drawable.mail_grey);
            this.drawer.closeDrawers();
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.colorPrimary));
            this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
            replaceFragment(new Alert(), getResources().getString(R.string.alert));
            return true;
        } else if (id == R.id.nav_wall) {
            Intent intent = getIntent();
            if (Objects.equals(intent.getStringExtra("benificiary"), "benificiary")) {
                this.toolbarText.setText("PRO");
            } else if (Objects.equals(intent.getStringExtra("godfather"), "godfather")) {
                this.toolbarText.setText("PRO");
            } else if (Objects.equals(intent.getStringExtra("professionel"), "professionel")) {
                this.toolbarText.setText("PRO");
            } else {
                this.toolbarText.setText("WALL");
            }
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_grey);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_blue);
            this.iv_mail.setImageResource(R.drawable.mail_grey);
            this.drawer.closeDrawers();
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_wall.setTextColor(getResources().getColor(R.color.colorPrimary));
            replaceFragment(new Wall(), getResources().getString(R.string.wall));
            return true;
        } else if (id == R.id.nav_wallpro) {
            Intent intent2 = getIntent();
            if (Objects.equals(intent2.getStringExtra("benificiary"), "benificiary")) {
                this.toolbarText.setText("PRO");
            } else if (Objects.equals(intent2.getStringExtra("godfather"), "godfather")) {
                this.toolbarText.setText("PRO");
            } else if (Objects.equals(intent2.getStringExtra("professionel"), "professionel")) {
                this.toolbarText.setText("PRO");
            } else {
                this.toolbarText.setText("WALL PRO");
            }
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_grey);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_blue);
            this.iv_mail.setImageResource(R.drawable.mail_grey);
            this.drawer.closeDrawers();
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_wall.setTextColor(getResources().getColor(R.color.colorPrimary));
            replaceFragment(new WallPro(), getResources().getString(R.string.wallpro));
            return true;
        } else if (id == R.id.nav_mail) {
            this.toolbarText.setText("INBOX");
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_grey);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_grey);
            this.iv_mail.setImageResource(R.drawable.mail_blue);
            this.drawer.closeDrawers();
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.colorPrimary));
            this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
            replaceFragment(new Inbox(), getResources().getString(R.string.inbox));
            return true;
        }
        //conseiller addition
        else if (id == R.id.nav_counselor) {
            this.toolbarText.setText("CONSEILLER");
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_grey);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_grey);
            this.iv_mail.setImageResource(R.drawable.mail_blue);
            this.drawer.closeDrawers();
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.colorPrimary));
            this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
            //replace fragment to conseiller
            replaceFragment(new Conseiller(), getResources().getString(R.string.conseiller));
            return true;
        }
        else if (id == R.id.nav_stats) {
            this.toolbarText.setText("STATS");
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_grey);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_grey);
            this.iv_mail.setImageResource(R.drawable.mail_grey);
            this.drawer.closeDrawers();
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
            replaceFragment(new Stats(), getResources().getString(R.string.stats));
            return true;
        } else if (id == R.id.nav_article) {
            this.toolbarText.setText("MY ARTICLES");
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_grey);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_grey);
            this.iv_wall.setImageResource(R.drawable.wall_grey);
            this.iv_mail.setImageResource(R.drawable.mail_grey);
            this.drawer.closeDrawers();
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
            replaceFragment(new Articles(), getResources().getString(R.string.article));
            return true;
        } else if (id == R.id.nav_cv) {
            this.toolbarText.setText("MY CV");
            this.iv_network.setImageResource(R.drawable.network_grey);
            this.iv_member.setImageResource(R.drawable.members_grey);
            this.iv_alert.setImageResource(R.drawable.alert_grey);
            this.iv_cv.setImageResource(R.drawable.cv_blue);
            this.iv_wall.setImageResource(R.drawable.wall_grey);
            this.iv_mail.setImageResource(R.drawable.mail_grey);
            this.drawer.closeDrawers();
            this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_cv.setTextColor(getResources().getColor(R.color.colorPrimary));
            this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
            this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
            replaceFragment(new Search_Cv(), getResources().getString(R.string.cv));
            return true;
        } else if (id == R.id.nav_chat) {
            return true;
        } else {
            if (id == R.id.nav_data) {
                this.toolbarText.setText("MY DATA");
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                this.drawer.closeDrawers();
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
                replaceFragment(new MyDataFragment(), getResources().getString(R.string.mydata));
                return true;
            } else if (id == R.id.nav_event) {
                this.toolbarText.setText("MY EVENTS");
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                this.drawer.closeDrawers();
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
                this.chat.setVisibility(8);
                this.add_event.setVisibility(0);
                replaceFragment(new fragment_Events(), getResources().getString(R.string.events));
                return true;
            } else if (id == R.id.nav_profile) {
                this.toolbarText.setText("MY PROFILE");
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                this.drawer.closeDrawers();
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
                replaceFragment(new Profile(), getResources().getString(R.string.profile));
                return true;
            } else if (id == R.id.nav_network) {
                this.toolbarText.setText("MY NETWORK");
                this.iv_network.setImageResource(R.drawable.network_blue);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                this.drawer.closeDrawers();
                this.tv_network.setTextColor(getResources().getColor(R.color.colorPrimary));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
                this.add_event.setVisibility(8);
                replaceFragment(new Network(), getResources().getString(R.string.network));
                return true;
            } else if (id == R.id.nav_agenda) {
                this.toolbarText.setText("MY AGENDA");
                this.iv_network.setImageResource(R.drawable.network_grey);
                this.iv_member.setImageResource(R.drawable.members_grey);
                this.iv_alert.setImageResource(R.drawable.alert_grey);
                this.iv_cv.setImageResource(R.drawable.cv_grey);
                this.iv_wall.setImageResource(R.drawable.wall_grey);
                this.iv_mail.setImageResource(R.drawable.mail_grey);
                this.drawer.closeDrawers();
                this.tv_network.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_member.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_cv.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_alert.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_mail.setTextColor(getResources().getColor(R.color.textGrey));
                this.tv_wall.setTextColor(getResources().getColor(R.color.textGrey));
                this.add_event.setVisibility(8);
                replaceFragment(new MyAgenda(), getResources().getString(R.string.agenda));
                return true;
            } else if (id == R.id.nav_logout) {
                this.drawer.closeDrawers();
                startActivity(new Intent(this, Login.class));
                App.getAppPreference().saveBoolean(AppConstants.IS_LOGIN, false);
                finish();
                return true;
            } else if (id != R.id.add_cer) {
                return true;
            } else {
                this.drawer.closeDrawers();
                startActivity(new Intent(this, AddCer.class));
                finish();
                return true;
            }
        }
    }
}
