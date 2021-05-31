package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by svitsindia on 13/02/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "";

    public static String Col_Id = "id";

    public static String SETTING_MASTER = "SettingMaster";
    public static String TITLE_MASTER = "TitleMaster";
    public static String OUTLET_MASTER = "OutletMaster";
    public static String RAGS_MASTER = "RagsMaster";
    public static String SIGNATURE_MASTER = "SignatureMaster";
    public static String CUSTOMER_LOGO_MASTER = "CustomerLogoMaster";
    public static String FUNC_REQ_STATUS_MASTER = "FuncReqStatusMaster";
    public static String GA_FORM_DATA_MASTER = "GAFormDataMaster";
    public static String SOURCE_MASTER = "SourceMaster";
    public static String SSB_HOTKEY_HEADER_MASTER = "SSBHotKeyHeaderMaster";
    public static String SSB_HOTKEY_DETAIL_MASTER = "SSBHotKeyDetailMaster";
    public static String SSB_HOTKEY_TRANSC_MASTER = "SSBHotKeyTranscMaster";
    public static String INTEREST_MASTER = "InterestMaster";
    public static String LOG_MASTER = "LogMaster";

    public static String SYNC_DONOR_MASTER = "SyncDonorMaster";
    public static String SYNC_BAGDROP_MASTER = "SyncBagDropMaster";
    public static String USER_MASTER = "UserMaster";

    public static String Col_SettingCode = "SettingCode";
    public static String Col_TextSetting = "TextSetting";
    public static String Col_IntSetting = "IntSetting";
    public static String Col_Title = "Title";
    public static String Col_OutletNo = "OutletNo";
    public static String Col_OutletName = "OutletName";
    public static String Col_OutletStatus = "OutletStatus";
    public static String Col_TypeId = "TypeId";
    public static String Col_Description = "Description";
    public static String Col_DonorID = "DonorID";
    public static String Col_ImgBase64String = "ImgBase64String";
    public static String Col_ImgHashMD5String = "ImgHashMD5String";
    public static String Col_FuncCallDate = "FuncCalledDate";
    public static String Col_FormData = "FormData";
    public static String Col_SourceID = "SourceID";
    public static String Col_HotKeyID = "HotKeyID";
    public static String Col_HotKeyDesc = "HotKeyDesc";
    public static String Col_StockCodeID = "StockCodeID";
    public static String Col_Price = "Price";
    public static String Col_InterestID = "InterestId";
    public static String Col_InterestName = "InterestName";
    public static String Col_LogDate = "LogDate";
    public static String Col_LogTime = "LogTime";
    public static String Col_SyncFlag = "SyncFlag";
    public static String Col_OperatorNo = "OperatorNo";
    public static String Col_Username = "Username";
    public static String Col_Password = "Password";
    public static String Col_Salt = "Salt";
    public static String Col_Rights29 = "Rights29";
    public static String Col_Rights30 = "Rights30";
    public static String Col_Rights47 = "Rights47";
    public static String Col_Rights999 = "Rights999";
    public static String Col_UniqueSwipeDetail = "UniqueSwipeDetail";
    public static String Col_NoOfBags = "NoOfBags";
    public static String Col_FunctionNo = "FunctionNo";

    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query_Setting_Master = "CREATE TABLE " +SETTING_MASTER
                                        + "("
                                            +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                            +Col_SettingCode+ " TEXT, "
                                            +Col_TextSetting+ " TEXT, "
                                            +Col_IntSetting+ " TEXT"
                                        +");";
        db.execSQL(Query_Setting_Master);

        String Query_Title_Master = "CREATE TABLE " +TITLE_MASTER
                                    + "("
                                        +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        +Col_Title+ " TEXT"
                                    +");";
        db.execSQL(Query_Title_Master);

        String Query_Outlet_Master = "CREATE TABLE " +OUTLET_MASTER
                                    + "("
                                        +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        +Col_OutletNo+ " TEXT, "
                                        +Col_OutletName+ " TEXT, "
                                        +Col_OutletStatus+ " TEXT"
                                    +");";
        db.execSQL(Query_Outlet_Master);

        String Query_Rags_Master = "CREATE TABLE " +RAGS_MASTER
                                    + "("
                                        +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        +Col_TypeId+ " TEXT, "
                                        +Col_Description+ " TEXT"
                                    +");";
        db.execSQL(Query_Rags_Master);

        String Query_Signature_Master = "CREATE TABLE " +SIGNATURE_MASTER
                                    + "("
                                        +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        +Col_DonorID+ " TEXT, "
                                        +Col_ImgBase64String+ " TEXT, "
                                        +Col_ImgHashMD5String+ " TEXT"
                                    +");";
        db.execSQL(Query_Signature_Master);

        String Query_Customer_Logo_Master = "CREATE TABLE " +CUSTOMER_LOGO_MASTER
                                    + "("
                                        +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        +Col_ImgBase64String+ " TEXT"
                                    +");";
        db.execSQL(Query_Customer_Logo_Master);

        String Query_Function_Request_Master = "CREATE TABLE " +FUNC_REQ_STATUS_MASTER
                                    + "("
                                        +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        +Col_FuncCallDate+ " TEXT, "
                                        +Col_FunctionNo+" INTEGER"
                                    +");";
        db.execSQL(Query_Function_Request_Master);

        String Query_GA_Form_Data_Master = "CREATE TABLE " +GA_FORM_DATA_MASTER
                                    + "("
                                        +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        +Col_FormData+ " TEXT"
                                    +");";
        db.execSQL(Query_GA_Form_Data_Master);

        String Query_Source_Master = "CREATE TABLE " +SOURCE_MASTER
                                    + "("
                                        +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        +Col_SourceID+ " TEXT, "
                                        +Col_Description+ " TEXT"
                                    +");";
        db.execSQL(Query_Source_Master);

        String Query_SSB_HotKey_Header_Master = "CREATE TABLE " +SSB_HOTKEY_HEADER_MASTER
                                                + "("
                                                    +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    +Col_HotKeyID+ " TEXT, "
                                                    +Col_HotKeyDesc+ " TEXT"
                                                +");";
        db.execSQL(Query_SSB_HotKey_Header_Master);

        String Query_SSB_HotKey_Detail_Master = "CREATE TABLE " +SSB_HOTKEY_DETAIL_MASTER
                                                + "("
                                                    +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    +Col_HotKeyID+ " TEXT, "
                                                    +Col_StockCodeID+ " TEXT, "
                                                    +Col_HotKeyDesc+ " TEXT"
                                                +");";
        db.execSQL(Query_SSB_HotKey_Detail_Master);

        String Query_SSB_HotKey_Trans_Master = "CREATE TABLE " +SSB_HOTKEY_TRANSC_MASTER
                                                + "("
                                                    +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    +Col_HotKeyID+ " TEXT, "
                                                    +Col_StockCodeID+ " TEXT, "
                                                    +Col_HotKeyDesc+ " TEXT, "
                                                    +Col_Price+ " TEXT"
                                                +");";
        db.execSQL(Query_SSB_HotKey_Trans_Master);

        String Query_Interest_Master = "CREATE TABLE " +INTEREST_MASTER
                + "("
                +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Col_InterestID+ " TEXT, "
                +Col_InterestName+ " TEXT"
                +");";
        db.execSQL(Query_Interest_Master);

        String Query_Log_Master = "CREATE TABLE " +LOG_MASTER
                + "("
                +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Col_LogDate+ " TEXT, "
                +Col_LogTime+ " TEXT, "
                +Col_Description+ " TEXT"
                +");";
        db.execSQL(Query_Log_Master);

        String Query_Sync_Donor_Master = "CREATE TABLE " +SYNC_DONOR_MASTER
                + "("
                +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Col_Description+ " TEXT, "
                +Col_UniqueSwipeDetail+ " TEXT, "
                +Col_ImgBase64String+ " TEXT, "
                +Col_ImgHashMD5String+ " TEXT, "
                +Col_SyncFlag+ " TEXT"
                +");";
        db.execSQL(Query_Sync_Donor_Master);

        String Query_Sync_BagDrop_Master = "CREATE TABLE " +SYNC_BAGDROP_MASTER
                + "("
                +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Col_NoOfBags+ " INTEGER, "
                +Col_UniqueSwipeDetail+ " TEXT, "
                +Col_DonorID+ " TEXT, "
                +Col_SyncFlag+ " TEXT"
                +");";
        db.execSQL(Query_Sync_BagDrop_Master);

        String Query_User_Master = "CREATE TABLE " +USER_MASTER
                + "("
                +Col_Id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Col_OperatorNo+ " TEXT, "
                +Col_Username+ " TEXT, "
                +Col_Password+ " TEXT, "
                +Col_Salt+ " TEXT, "
                +Col_Rights29+ " TEXT, "
                +Col_Rights30+ " TEXT, "
                +Col_Rights47+ " TEXT, "
                +Col_Rights999+ " TEXT"
                +");";
        db.execSQL(Query_User_Master);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
