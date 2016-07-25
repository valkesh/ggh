package com.quorg.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.quorg.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jigar.pandya on 18/7/16.
 */
public class CommonClass {

    static ProgressDialog pdlg;

    public static boolean checkInternetConnetion(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }


    public static void showLoading(Context c, String s) {

        pdlg = new ProgressDialog(c);
        pdlg.setMessage(s);
        pdlg.setCancelable(false);
        pdlg.show();

    }

    public static void closeLoading()
    {
        if(pdlg!=null)
        {
            pdlg.dismiss();
        }
    }

    public static void showToast(Context c, String msg, View v)
    {

        Snackbar snackbar = Snackbar
                .make(v, msg, Snackbar.LENGTH_SHORT);

        snackbar.getView().setBackgroundColor(c.getResources().getColor(R.color.colorPrimary));

        snackbar.show();


    }

    public static void showSimpleToast(Context c,String msg)
    {
        Toast.makeText(c,msg, Toast.LENGTH_SHORT).show();
    }



    public static boolean ValidateTask(Context c,String uemailid2, String upassword2,View v) {
        try {
            if (uemailid2.trim().length() > 0) {
                if (TextValidator.isAValidEmail(uemailid2)) {
                    String[] domain = {".aero", ".asia", ".biz",
                            ".cat", ".com", ".coop", ".edu",
                            ".gov", ".info", ".int", ".jobs",
                            ".mil", ".mobi", ".museum", ".name",
                            ".net", ".org", ".pro", ".tel",
                            ".travel", ".ac", ".ad", ".ae", ".af",
                            ".ag", ".ai", ".al", ".am", ".an",
                            ".ao", ".aq", ".ar", ".as", ".at",
                            ".au", ".aw", ".ax", ".az", ".ba",
                            ".bb", ".bd", ".be", ".bf", ".bg",
                            ".bh", ".bi", ".bj", ".bm", ".bn",
                            ".bo", ".br", ".bs", ".bt", ".bv",
                            ".bw", ".by", ".bz", ".ca", ".cc",
                            ".cd", ".cf", ".cg", ".ch", ".ci",
                            ".ck", ".cl", ".cm", ".cn", ".co",
                            ".cr", ".cu", ".cv", ".cx", ".cy",
                            ".cz", ".de", ".dj", ".dk", ".dm",
                            ".do", ".dz", ".ec", ".ee", ".eg",
                            ".er", ".es", ".et", ".eu", ".fi",
                            ".fj", ".fk", ".fm", ".fo", ".fr",
                            ".ga", ".gb", ".gd", ".ge", ".gf",
                            ".gg", ".gh", ".gi", ".gl", ".gm",
                            ".gn", ".gp", ".gq", ".gr", ".gs",
                            ".gt", ".gu", ".gw", ".gy", ".hk",
                            ".hm", ".hn", ".hr", ".ht", ".hu",
                            ".id", ".ie", " No", ".il", ".im",
                            ".in", ".io", ".iq", ".ir", ".is",
                            ".it", ".je", ".jm", ".jo", ".jp",
                            ".ke", ".kg", ".kh", ".ki", ".km",
                            ".kn", ".kp", ".kr", ".kw", ".ky",
                            ".kz", ".la", ".lb", ".lc", ".li",
                            ".lk", ".lr", ".ls", ".lt", ".lu",
                            ".lv", ".ly", ".ma", ".mc", ".md",
                            ".me", ".mg", ".mh", ".mk", ".ml",
                            ".mm", ".mn", ".mo", ".mp", ".mq",
                            ".mr", ".ms", ".mt", ".mu", ".mv",
                            ".mw", ".mx", ".my", ".mz", ".na",
                            ".nc", ".ne", ".nf", ".ng", ".ni",
                            ".nl", ".no", ".np", ".nr", ".nu",
                            ".nz", ".om", ".pa", ".pe", ".pf",
                            ".pg", ".ph", ".pk", ".pl", ".pm",
                            ".pn", ".pr", ".ps", ".pt", ".pw",
                            ".py", ".qa", ".re", ".ro", ".rs",
                            ".ru", ".rw", ".sa", ".sb", ".sc",
                            ".sd", ".se", ".sg", ".sh", ".si",
                            ".sj", ".sk", ".sl", ".sm", ".sn",
                            ".so", ".sr", ".st", ".su", ".sv",
                            ".sy", ".sz", ".tc", ".td", ".tf",
                            ".tg", ".th", ".tj", ".tk", ".tl",
                            ".tm", ".tn", ".to", ".tp", ".tr",
                            ".tt", ".tv", ".tw", ".tz", ".ua",
                            ".ug", ".uk", ".us", ".uy", ".uz",
                            ".va", ".vc", ".ve", ".vg", ".vi",
                            ".vn", ".vu", ".wf", ".ws", ".ye",
                            ".yt", ".za", ".zm", ".zw"};
                    List<String> list = Arrays.asList(domain);
                    String tmp = uemailid2.substring(
                            uemailid2.lastIndexOf("."), uemailid2.length());
                    if (list.contains("" + tmp)) {
                        if (upassword2.trim().length() > 0)
                        {
                            return true;
                        }
                        else
                        {
                            showToast(c,"Please enter password.",v);
                            return false;
                        }
                    } else {
                        showToast(c,"Please enter valid email address.",v);
                        return false;
                    }
                } else {
                    showToast(c,"Please enter valid email address.",v);
                    return false;
                }
            } else {
                showToast(c,"Please enter email address.",v);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean ValidateRegister(Context c,String uemailid2, String upassword2,String cpassword,View v) {
        try {
            if (uemailid2.trim().length() > 0)
            {
                if (TextValidator.isAValidEmail(uemailid2)) {
                    String[] domain = {".aero", ".asia", ".biz",
                            ".cat", ".com", ".coop", ".edu",
                            ".gov", ".info", ".int", ".jobs",
                            ".mil", ".mobi", ".museum", ".name",
                            ".net", ".org", ".pro", ".tel",
                            ".travel", ".ac", ".ad", ".ae", ".af",
                            ".ag", ".ai", ".al", ".am", ".an",
                            ".ao", ".aq", ".ar", ".as", ".at",
                            ".au", ".aw", ".ax", ".az", ".ba",
                            ".bb", ".bd", ".be", ".bf", ".bg",
                            ".bh", ".bi", ".bj", ".bm", ".bn",
                            ".bo", ".br", ".bs", ".bt", ".bv",
                            ".bw", ".by", ".bz", ".ca", ".cc",
                            ".cd", ".cf", ".cg", ".ch", ".ci",
                            ".ck", ".cl", ".cm", ".cn", ".co",
                            ".cr", ".cu", ".cv", ".cx", ".cy",
                            ".cz", ".de", ".dj", ".dk", ".dm",
                            ".do", ".dz", ".ec", ".ee", ".eg",
                            ".er", ".es", ".et", ".eu", ".fi",
                            ".fj", ".fk", ".fm", ".fo", ".fr",
                            ".ga", ".gb", ".gd", ".ge", ".gf",
                            ".gg", ".gh", ".gi", ".gl", ".gm",
                            ".gn", ".gp", ".gq", ".gr", ".gs",
                            ".gt", ".gu", ".gw", ".gy", ".hk",
                            ".hm", ".hn", ".hr", ".ht", ".hu",
                            ".id", ".ie", " No", ".il", ".im",
                            ".in", ".io", ".iq", ".ir", ".is",
                            ".it", ".je", ".jm", ".jo", ".jp",
                            ".ke", ".kg", ".kh", ".ki", ".km",
                            ".kn", ".kp", ".kr", ".kw", ".ky",
                            ".kz", ".la", ".lb", ".lc", ".li",
                            ".lk", ".lr", ".ls", ".lt", ".lu",
                            ".lv", ".ly", ".ma", ".mc", ".md",
                            ".me", ".mg", ".mh", ".mk", ".ml",
                            ".mm", ".mn", ".mo", ".mp", ".mq",
                            ".mr", ".ms", ".mt", ".mu", ".mv",
                            ".mw", ".mx", ".my", ".mz", ".na",
                            ".nc", ".ne", ".nf", ".ng", ".ni",
                            ".nl", ".no", ".np", ".nr", ".nu",
                            ".nz", ".om", ".pa", ".pe", ".pf",
                            ".pg", ".ph", ".pk", ".pl", ".pm",
                            ".pn", ".pr", ".ps", ".pt", ".pw",
                            ".py", ".qa", ".re", ".ro", ".rs",
                            ".ru", ".rw", ".sa", ".sb", ".sc",
                            ".sd", ".se", ".sg", ".sh", ".si",
                            ".sj", ".sk", ".sl", ".sm", ".sn",
                            ".so", ".sr", ".st", ".su", ".sv",
                            ".sy", ".sz", ".tc", ".td", ".tf",
                            ".tg", ".th", ".tj", ".tk", ".tl",
                            ".tm", ".tn", ".to", ".tp", ".tr",
                            ".tt", ".tv", ".tw", ".tz", ".ua",
                            ".ug", ".uk", ".us", ".uy", ".uz",
                            ".va", ".vc", ".ve", ".vg", ".vi",
                            ".vn", ".vu", ".wf", ".ws", ".ye",
                            ".yt", ".za", ".zm", ".zw"};
                    List<String> list = Arrays.asList(domain);
                    String tmp = uemailid2.substring(
                            uemailid2.lastIndexOf("."), uemailid2.length());
                    if (list.contains("" + tmp)) {
                        if (upassword2.trim().length() > 0)
                        {
                            if(cpassword.trim().length()>0)
                            {
                               if(upassword2.equals(cpassword))
                               {
                                   return true;
                               }
                               else
                               {
                                   showToast(c,"Password and confirm password doesn't match.",v);
                                   return false;
                               }
                            }
                            else
                            {
                                showToast(c,"Please enter confirm password.",v);
                                return false;
                            }

                        }
                        else
                        {
                            showToast(c,"Please enter password.",v);
                            return false;
                        }
                    } else {
                        showToast(c,"Please enter valid email address.",v);
                        return false;
                    }
                } else {
                    showToast(c,"Please enter valid email address.",v);
                    return false;
                }
            } else {
                showToast(c,"Please enter email address.",v);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public static boolean ValidateEmail(Context c,String uemailid2,View v) {
        try {
            if (uemailid2.trim().length() > 0) {
                if (TextValidator.isAValidEmail(uemailid2)) {
                    String[] domain = {".aero", ".asia", ".biz",
                            ".cat", ".com", ".coop", ".edu",
                            ".gov", ".info", ".int", ".jobs",
                            ".mil", ".mobi", ".museum", ".name",
                            ".net", ".org", ".pro", ".tel",
                            ".travel", ".ac", ".ad", ".ae", ".af",
                            ".ag", ".ai", ".al", ".am", ".an",
                            ".ao", ".aq", ".ar", ".as", ".at",
                            ".au", ".aw", ".ax", ".az", ".ba",
                            ".bb", ".bd", ".be", ".bf", ".bg",
                            ".bh", ".bi", ".bj", ".bm", ".bn",
                            ".bo", ".br", ".bs", ".bt", ".bv",
                            ".bw", ".by", ".bz", ".ca", ".cc",
                            ".cd", ".cf", ".cg", ".ch", ".ci",
                            ".ck", ".cl", ".cm", ".cn", ".co",
                            ".cr", ".cu", ".cv", ".cx", ".cy",
                            ".cz", ".de", ".dj", ".dk", ".dm",
                            ".do", ".dz", ".ec", ".ee", ".eg",
                            ".er", ".es", ".et", ".eu", ".fi",
                            ".fj", ".fk", ".fm", ".fo", ".fr",
                            ".ga", ".gb", ".gd", ".ge", ".gf",
                            ".gg", ".gh", ".gi", ".gl", ".gm",
                            ".gn", ".gp", ".gq", ".gr", ".gs",
                            ".gt", ".gu", ".gw", ".gy", ".hk",
                            ".hm", ".hn", ".hr", ".ht", ".hu",
                            ".id", ".ie", " No", ".il", ".im",
                            ".in", ".io", ".iq", ".ir", ".is",
                            ".it", ".je", ".jm", ".jo", ".jp",
                            ".ke", ".kg", ".kh", ".ki", ".km",
                            ".kn", ".kp", ".kr", ".kw", ".ky",
                            ".kz", ".la", ".lb", ".lc", ".li",
                            ".lk", ".lr", ".ls", ".lt", ".lu",
                            ".lv", ".ly", ".ma", ".mc", ".md",
                            ".me", ".mg", ".mh", ".mk", ".ml",
                            ".mm", ".mn", ".mo", ".mp", ".mq",
                            ".mr", ".ms", ".mt", ".mu", ".mv",
                            ".mw", ".mx", ".my", ".mz", ".na",
                            ".nc", ".ne", ".nf", ".ng", ".ni",
                            ".nl", ".no", ".np", ".nr", ".nu",
                            ".nz", ".om", ".pa", ".pe", ".pf",
                            ".pg", ".ph", ".pk", ".pl", ".pm",
                            ".pn", ".pr", ".ps", ".pt", ".pw",
                            ".py", ".qa", ".re", ".ro", ".rs",
                            ".ru", ".rw", ".sa", ".sb", ".sc",
                            ".sd", ".se", ".sg", ".sh", ".si",
                            ".sj", ".sk", ".sl", ".sm", ".sn",
                            ".so", ".sr", ".st", ".su", ".sv",
                            ".sy", ".sz", ".tc", ".td", ".tf",
                            ".tg", ".th", ".tj", ".tk", ".tl",
                            ".tm", ".tn", ".to", ".tp", ".tr",
                            ".tt", ".tv", ".tw", ".tz", ".ua",
                            ".ug", ".uk", ".us", ".uy", ".uz",
                            ".va", ".vc", ".ve", ".vg", ".vi",
                            ".vn", ".vu", ".wf", ".ws", ".ye",
                            ".yt", ".za", ".zm", ".zw"};
                    List<String> list = Arrays.asList(domain);
                    String tmp = uemailid2.substring(
                            uemailid2.lastIndexOf("."), uemailid2.length());
                    if (list.contains("" + tmp)) {
                        return true;
                    } else {
                        showToast(c,"Please enter valid email address.",v);
                        return false;
                    }
                } else {
                    showToast(c,"Please enter valid email address.",v);
                    return false;
                }
            } else {
                showToast(c,"Please enter email address.",v);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}