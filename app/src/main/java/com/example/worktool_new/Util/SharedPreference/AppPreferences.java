package com.example.worktool_new.Util.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.worktool_new.Models.GetEngagementModel;
import com.example.worktool_new.Models.JsonModel;
import com.example.worktool_new.Models.NewCvModel;
import com.example.worktool_new.Models.getskills.CustomSkillModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class AppPreferences {
    private static AppPreferences appPreference;
    private SharedPreferences sharedPreferences;

    private AppPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(AppConstants.SHARED_PREFERENCES_KEY, 0);
    }

    public static AppPreferences init(Context context) {
        if (appPreference == null) {
            appPreference = new AppPreferences(context);
        }
        return appPreference;
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return this.sharedPreferences.getString(key, "");
    }

    public void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        return this.sharedPreferences.getBoolean(key, false);
    }

    public void saveArrayList(ArrayList<NewCvModel> list) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString("cvlist", new Gson().toJson((Object) list));
        editor.apply();
    }

    public ArrayList<NewCvModel> getArrayList() {
        return (ArrayList) new Gson().fromJson(this.sharedPreferences.getString("cvlist", (String) null), new TypeToken<ArrayList<NewCvModel>>() {
        }.getType());
    }

    public void saveEngagementArrayList(ArrayList<GetEngagementModel.Datum> list) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString("engagementlist", new Gson().toJson((Object) list));
        editor.apply();
    }

    public ArrayList<GetEngagementModel.Datum> getEngagementArrayList() {
        return (ArrayList) new Gson().fromJson(this.sharedPreferences.getString("engagementlist", (String) null), new TypeToken<ArrayList<GetEngagementModel.Datum>>() {
        }.getType());
    }

    public void saveCustomSkilllist(ArrayList<JsonModel> list) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString("skillist", new Gson().toJson((Object) list));
        editor.apply();
    }

    public ArrayList<JsonModel> getcustomSkilllist() {
        return (ArrayList) new Gson().fromJson(this.sharedPreferences.getString("skillist", (String) null), new TypeToken<ArrayList<JsonModel>>() {
        }.getType());
    }

    public void saveImportCvList(ArrayList<CustomSkillModel> list) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString("cutomSkillList", new Gson().toJson((Object) list));
        editor.apply();
    }

    public ArrayList<CustomSkillModel> getImportCvList() {
        return (ArrayList) new Gson().fromJson(this.sharedPreferences.getString("cutomSkillList", (String) null), new TypeToken<ArrayList<CustomSkillModel>>() {
        }.getType());
    }
}
