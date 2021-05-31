package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import dataModels.HotKey;
import dataModels.HotKeyDetails;
import dataModels.HotKeyTransaction;
import singleton.GlobalApps;
import timber.log.Timber;
import utility.Crypto;

/**
 * Created by svitsindia on 13/02/17.
 */

public class DataSource {

    public DatabaseHelper databaseHelper;
    public SQLiteDatabase database;
    public Context mContext;

    /**
     * Constructor
     *
     * @param context
     */
    public DataSource(Context context) {
        databaseHelper = new DatabaseHelper(context);
        mContext = context;
    }

    /**
     * Insert Data into SettingMaster
     *
     * @param settingCode
     * @param textSetting
     * @param intSetting
     */
    public void insertSettingData(String settingCode, String textSetting, String intSetting) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.Col_SettingCode, settingCode);
            values.put(DatabaseHelper.Col_TextSetting, textSetting);
            values.put(DatabaseHelper.Col_IntSetting, intSetting);

            database.insert(DatabaseHelper.SETTING_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    /**
     * Insert data into OutletMaster
     *
     * @param outletNo
     * @param outletName
     * @param outletStatus
     */
    public void insertOutletData(String outletNo, String outletName, String outletStatus) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.Col_OutletNo, outletNo);
            values.put(DatabaseHelper.Col_OutletName, outletName);
            values.put(DatabaseHelper.Col_OutletStatus, outletStatus);

            database.insert(DatabaseHelper.OUTLET_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    /**
     * Insert data into TitleMaster
     *
     * @param title
     */
    public void insertTitleData(String title) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.Col_Title, title);

            database.insert(DatabaseHelper.TITLE_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    /**
     * Insert data into RagsMaster
     *
     * @param typeId
     * @param desc
     */
    public void insertRagsData(String typeId, String desc) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.Col_TypeId, typeId);
            values.put(DatabaseHelper.Col_Description, desc);

            database.insert(DatabaseHelper.RAGS_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    /**
     * Insert data into FuncCallDate
     *
     * @param date
     */
    public void insertFuncCallDate(String date, int functionNo) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_FuncCallDate, date);
            values.put(DatabaseHelper.Col_FunctionNo, functionNo);
            database.insert(DatabaseHelper.FUNC_REQ_STATUS_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public void insertSourceData(String sourceId, String sourceDesc) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_SourceID, sourceId);
            values.put(DatabaseHelper.Col_Description, sourceDesc);
            database.insert(DatabaseHelper.SOURCE_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public void insertHotKeyHeaderData(String hotKeyId, String hotKeyDesc) {
        try {
//            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_HotKeyID, hotKeyId);
            values.put(DatabaseHelper.Col_HotKeyDesc, hotKeyDesc);
            database.insert(DatabaseHelper.SSB_HOTKEY_HEADER_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            if (database != null && database.isOpen()) {
//                database.close();
//            }
        }
    }

    public void insertHotKeyDetailData(String hotKeyId, String stockCodeId, String hotKeyDesc) {
        try {
//            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_HotKeyID, hotKeyId);
            values.put(DatabaseHelper.Col_StockCodeID, stockCodeId);
            values.put(DatabaseHelper.Col_HotKeyDesc, hotKeyDesc);
            database.insert(DatabaseHelper.SSB_HOTKEY_DETAIL_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            if (database != null && database.isOpen()) {
//                database.close();
//            }
        }
    }

    public void insertHotKeyTransData(String hotKeyId, String stockCodeId, String hotKeyDesc, String price) {
        try {
//            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_HotKeyID, hotKeyId);
            values.put(DatabaseHelper.Col_StockCodeID, stockCodeId);
            values.put(DatabaseHelper.Col_HotKeyDesc, hotKeyDesc);
            values.put(DatabaseHelper.Col_Price, price);
            database.insert(DatabaseHelper.SSB_HOTKEY_TRANSC_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            if (database != null && database.isOpen()) {
//                database.close();
//            }
        }
    }

    public void insertInterestData(String interestId, String interestName) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_InterestID, interestId);
            values.put(DatabaseHelper.Col_InterestName, interestName);
            database.insert(DatabaseHelper.INTEREST_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public void insertDatabaseLog(String desc) {
        if (GlobalApps.getInstance().getDatabaseLogStatus()) {
            try {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String datetime = dateformat.format(c.getTime());
                String[] arrDateTime = datetime.split(" ");
                database = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.Col_LogDate, arrDateTime[0].trim());
                values.put(DatabaseHelper.Col_LogTime, arrDateTime[1].trim());
                values.put(DatabaseHelper.Col_Description, desc);
                database.insert(DatabaseHelper.LOG_MASTER, null, values);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (database != null && database.isOpen()) {
                    database.close();
                }
            }
        }
    }

    public void TMPInsertDatabaseLog(String date, String desc) {
        if (GlobalApps.getInstance().getDatabaseLogStatus()) {
            try {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String datetime = dateformat.format(c.getTime());
                String[] arrDateTime = datetime.split(" ");
                database = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.Col_LogDate, date);
                values.put(DatabaseHelper.Col_LogTime, arrDateTime[1].trim());
                values.put(DatabaseHelper.Col_Description, desc);
                database.insert(DatabaseHelper.LOG_MASTER, null, values);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (database != null && database.isOpen()) {
                    database.close();
                }
            }
        }
    }

    public boolean insertDonorDataOffline(String desc, String swipe, String base64, String md5Hash) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_Description, desc);
            values.put(DatabaseHelper.Col_UniqueSwipeDetail, swipe);
            values.put(DatabaseHelper.Col_ImgBase64String, base64);
            values.put(DatabaseHelper.Col_ImgHashMD5String, md5Hash);
            values.put(DatabaseHelper.Col_SyncFlag, "0");
            database.insert(DatabaseHelper.SYNC_DONOR_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return true;
    }

    public long getCountOfInsertDonorDataOffline() {
        try {
            database = databaseHelper.getWritableDatabase();
            long count = DatabaseUtils.queryNumEntries(database, DatabaseHelper.SYNC_DONOR_MASTER);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return 0;
    }

    public long getCountOfFuncReqStatusMaster(int functionNo) {
        try {
            database = databaseHelper.getWritableDatabase();
            long count = DatabaseUtils.queryNumEntries(database, DatabaseHelper.FUNC_REQ_STATUS_MASTER, DatabaseHelper.Col_FunctionNo + "=" + functionNo);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return 0;
    }

    public void updateFuncCallDate(String date, int functionNo) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_FuncCallDate, date);
            database.update(DatabaseHelper.FUNC_REQ_STATUS_MASTER, values, DatabaseHelper.Col_FunctionNo + "=" + functionNo , null);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public String getDataOfFuncReqStatusMaster(int functionNo) {
        String result = "";
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.FUNC_REQ_STATUS_MASTER,
                    new String[]{DatabaseHelper.Col_FuncCallDate},
                    DatabaseHelper.Col_FunctionNo + "=" + functionNo, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String desc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_FuncCallDate));
                result = desc;
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public void insertGAFormData(String data) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_FormData, data);
            database.insert(DatabaseHelper.GA_FORM_DATA_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public long getCountOfGAFormData() {
        try {
            database = databaseHelper.getWritableDatabase();
            long count = DatabaseUtils.queryNumEntries(database, DatabaseHelper.GA_FORM_DATA_MASTER);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return 0;
    }

    public void updateGAFormData(String data) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_FormData, data);
            database.update(DatabaseHelper.GA_FORM_DATA_MASTER, values, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public String getDataOfGAForm() {
        String result = "";
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.GA_FORM_DATA_MASTER, new String[]{DatabaseHelper.Col_FormData}, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String desc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_FormData));
                result = desc;
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public void insertCustLogoImgBase64(String data) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_ImgBase64String, data);
            database.insert(DatabaseHelper.CUSTOMER_LOGO_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public String getCustLogoBase64String() {
        String result = "";
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.CUSTOMER_LOGO_MASTER, new String[]{DatabaseHelper.Col_ImgBase64String}, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String desc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_ImgBase64String));
                result = desc;
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public long getCountOfCustLogo() {
        try {
            database = databaseHelper.getWritableDatabase();
            long count = DatabaseUtils.queryNumEntries(database, DatabaseHelper.CUSTOMER_LOGO_MASTER);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return 0;
    }

    public long getCountOfRagsMaster() {
        try {
            database = databaseHelper.getWritableDatabase();
            long count = DatabaseUtils.queryNumEntries(database, DatabaseHelper.RAGS_MASTER);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return 0;
    }

    public long getCountOfLogMaster() {
        try {
            database = databaseHelper.getWritableDatabase();
            long count = DatabaseUtils.queryNumEntries(database, DatabaseHelper.LOG_MASTER);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return 0;
    }

    public void insertSignatureBase64(String donorId, String base64, String md5Hash) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_DonorID, donorId);
            values.put(DatabaseHelper.Col_ImgBase64String, base64);
            values.put(DatabaseHelper.Col_ImgHashMD5String, md5Hash);
            database.insert(DatabaseHelper.SIGNATURE_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public HashMap<String, String> getSignatureDataFromDonorID(String strDonorID) {
        HashMap<String, String> result = new HashMap<String, String>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SIGNATURE_MASTER, new String[]{DatabaseHelper.Col_ImgBase64String, DatabaseHelper.Col_ImgHashMD5String}, DatabaseHelper.Col_DonorID + "=" + strDonorID, null, null, null, null);
            if (cursor.moveToNext()) {
                result.put("Base64", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_ImgBase64String)));
                result.put("Md5Hash", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_ImgHashMD5String)));
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public HashMap<String, String> getSignatureData() {
        HashMap<String, String> result = new HashMap<String, String>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SIGNATURE_MASTER, new String[]{DatabaseHelper.Col_ImgBase64String, DatabaseHelper.Col_ImgHashMD5String, DatabaseHelper.Col_DonorID}, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                result.put("Base64", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_ImgBase64String)));
                result.put("Md5Hash", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_ImgHashMD5String)));
                result.put("DonorID", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_DonorID)));
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public void deleteSignatureFromDonorID(String donorID) {
        try {
            database = databaseHelper.getWritableDatabase();
            database.delete(DatabaseHelper.SIGNATURE_MASTER, DatabaseHelper.Col_DonorID + "=" + donorID, null);
            database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public long getCountOfSignatureMst() {
        try {
            database = databaseHelper.getWritableDatabase();
            long count = DatabaseUtils.queryNumEntries(database, DatabaseHelper.SIGNATURE_MASTER);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return 0;
    }

    public Cursor exportLogData(int days) {
        try {
            String earlierDate = GlobalApps.getInstance().getEarlierDateFromCurrentDate(days);
            database = databaseHelper.getWritableDatabase();
            Cursor cursorData = database.
                    rawQuery("SELECT * FROM " + DatabaseHelper.LOG_MASTER + " WHERE " + DatabaseHelper.Col_LogDate + "> '" + earlierDate + "'",
                            new String[]{});
            return cursorData;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public void insertUserCredential(String operatorNo, String username, String password,
                                     String salt, String rights29, String rights30,
                                     String rights47, String rights999) {
        try {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_OperatorNo, operatorNo);
            values.put(DatabaseHelper.Col_Username, username);
            values.put(DatabaseHelper.Col_Password, password);
            values.put(DatabaseHelper.Col_Salt, salt);
            values.put(DatabaseHelper.Col_Rights29, rights29);
            values.put(DatabaseHelper.Col_Rights30, rights30);
            values.put(DatabaseHelper.Col_Rights47, rights47);
            values.put(DatabaseHelper.Col_Rights999, rights999);
            database.insert(DatabaseHelper.USER_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openDBConnection() {
        try {
            database = databaseHelper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDBConnection() {
        try {
            if (database != null && database.isOpen()) {
                database.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> validateOfflineUserCredential(String username, String password) {

        HashMap<String, String> resultSaltOperator = getSaltOperatorNoFromUsername(username);
        if (resultSaltOperator.size() != 0) {
            String build = password + resultSaltOperator.get("Col_Salt") + resultSaltOperator.get("Col_OperatorNo");
            String pwd = "";
            try {
                pwd = GlobalApps.getInstance().computeSHA256Hash(build);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            password = pwd.toUpperCase();
        }
        HashMap<String, String> result = new HashMap<String, String>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.USER_MASTER,
                    new String[] {
                        DatabaseHelper.Col_OperatorNo,
                        DatabaseHelper.Col_Salt,
                        DatabaseHelper.Col_Rights29,
                        DatabaseHelper.Col_Rights30,
                        DatabaseHelper.Col_Rights47,
                        DatabaseHelper.Col_Rights999
                    },
                    DatabaseHelper.Col_Username + "=? AND " + DatabaseHelper.Col_Password + "=?" ,
                    new String[] {username, password}, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                result.put("Col_OperatorNo", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_OperatorNo)));
                result.put("Col_Salt", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Salt)));
                result.put("Col_Rights29", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Rights29)));
                result.put("Col_Rights30", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Rights30)));
                result.put("Col_Rights47", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Rights47)));
                result.put("Col_Rights999", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Rights999)));
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public HashMap<String, String> getSaltOperatorNoFromUsername(String username) {
        HashMap<String, String> result = new HashMap<String, String>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.USER_MASTER,
                    new String[] {
                            DatabaseHelper.Col_OperatorNo,
                            DatabaseHelper.Col_Salt
                    },
                    DatabaseHelper.Col_Username + "=?",
                    new String[] {username}, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                result.put("Col_OperatorNo", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_OperatorNo)));
                result.put("Col_Salt", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Salt)));
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public HashMap<String, String> getDonorDetailUsingSwipeDetailOffline(String swipeDetail) {
        HashMap<String, String> result = new HashMap<String, String>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SYNC_DONOR_MASTER,
                    new String[]{DatabaseHelper.Col_Description},
                    DatabaseHelper.Col_UniqueSwipeDetail + "='" + swipeDetail.toUpperCase() + "'",
                    null, null, null, null);

            String cipherText = "";
            if (cursor.moveToNext()) {
                cipherText = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Description));
            }

            if (!cipherText.isEmpty()) {

                String firstLastRemove = cipherText.substring(1, cipherText.length() - 1);
                String subIV = firstLastRemove.substring(0, 16);
                String subRes = firstLastRemove.substring(16);
                String dec = Crypto.decrypt(GlobalApps.getInstance().getSecurityKey(), subIV, subRes);
                ArrayList<String> resArray = GlobalApps.getInstance().splitUsingDelimiter(dec, GlobalApps.Constant.Identifiers.DS1);
                Timber.v("" + resArray);
                String rawData = resArray.get(3);
                ArrayList<String> rawDataArray = GlobalApps.getInstance().splitUsingDelimiter(rawData, GlobalApps.Constant.Identifiers.DS3);
                Timber.v("" + rawDataArray);

                result.put("Title", rawDataArray.get(2));
                result.put("First", rawDataArray.get(3));
                result.put("Last", rawDataArray.get(5));
            }

            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public boolean insertBagDropDataOffline(int noOfBag, String swipe) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_NoOfBags, noOfBag);
            values.put(DatabaseHelper.Col_UniqueSwipeDetail, swipe.toUpperCase());
            values.put(DatabaseHelper.Col_DonorID, "");
            values.put(DatabaseHelper.Col_SyncFlag, "0");
            database.insert(DatabaseHelper.SYNC_BAGDROP_MASTER, null, values);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return true;
    }

    public long getCountOfBagDropDataOffline() {
        try {
            database = databaseHelper.getWritableDatabase();
            long count = DatabaseUtils.queryNumEntries(database, DatabaseHelper.SYNC_BAGDROP_MASTER);
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return 0;
    }

    public ArrayList<HashMap<String, String>> getDonorDataOffline() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SYNC_DONOR_MASTER,
                    new String[]{
                            DatabaseHelper.Col_Description,
                            DatabaseHelper.Col_UniqueSwipeDetail,
                            DatabaseHelper.Col_ImgBase64String,
                            DatabaseHelper.Col_ImgHashMD5String,
                            DatabaseHelper.Col_SyncFlag
                    },
                    null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String desc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Description));
                String uniqueSwipe = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_UniqueSwipeDetail));
                String base64 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_ImgBase64String));
                String md5Hash = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_ImgHashMD5String));
                String flag = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_SyncFlag));
                HashMap<String, String> data = new HashMap<>();
                data.put("Col_Description", desc);
                data.put("Col_UniqueSwipeDetail", uniqueSwipe);
                data.put("Col_ImgBase64String", base64);
                data.put("Col_ImgHashMD5String", md5Hash);
                data.put("Col_SyncFlag", flag);
                result.add(data);
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public ArrayList<HashMap<String, String>> getBagDropDataOffline() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SYNC_BAGDROP_MASTER,
                    new String[]{
                            DatabaseHelper.Col_NoOfBags,
                            DatabaseHelper.Col_UniqueSwipeDetail,
                            DatabaseHelper.Col_DonorID,
                            DatabaseHelper.Col_SyncFlag
                    },
                    null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String noOfBag = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_NoOfBags));
                String uniqueSwipe = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_UniqueSwipeDetail));
                String donorID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_DonorID));
                String flag = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_SyncFlag));
                HashMap<String, String> data = new HashMap<>();
                data.put("Col_NoOfBags", noOfBag);
                data.put("Col_UniqueSwipeDetail", uniqueSwipe);
                data.put("Col_DonorID", donorID);
                data.put("Col_SyncFlag", flag);
                result.add(data);
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public HashMap<String, String> getTopMostDonorDataOffline() {
        HashMap<String, String> result = new HashMap<>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SYNC_DONOR_MASTER,
                    new String[]{
                            DatabaseHelper.Col_Description,
                            DatabaseHelper.Col_UniqueSwipeDetail,
                            DatabaseHelper.Col_ImgBase64String,
                            DatabaseHelper.Col_ImgHashMD5String,
                            DatabaseHelper.Col_SyncFlag
                    },
                    null, null, null, null, DatabaseHelper.Col_Id + " ASC", "1");
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String desc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Description));
                String uniqueSwipe = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_UniqueSwipeDetail));
                String base64 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_ImgBase64String));
                String md5Hash = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_ImgHashMD5String));
                String flag = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_SyncFlag));
                result.put("Col_Description", desc);
                result.put("Col_UniqueSwipeDetail", uniqueSwipe);
                result.put("Col_ImgBase64String", base64);
                result.put("Col_ImgHashMD5String", md5Hash);
                result.put("Col_SyncFlag", flag);
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public void updateDonorDataSyncStatus(String swipe, String status) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_SyncFlag, status);
            database.update(DatabaseHelper.SYNC_DONOR_MASTER, values, DatabaseHelper.Col_UniqueSwipeDetail + "='" + swipe.toUpperCase() + "'", null);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public void deleteDonorDataByUniqueSwipeDetail(String swipe) {
        try {
            database = databaseHelper.getWritableDatabase();
            database.delete(DatabaseHelper.SYNC_DONOR_MASTER, DatabaseHelper.Col_UniqueSwipeDetail + "='" + swipe.toUpperCase() + "'", null);
            database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public HashMap<String, String> getTopMostBagDropDataOffline() {
        HashMap<String, String> result = new HashMap<>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SYNC_BAGDROP_MASTER,
                    new String[]{
                            DatabaseHelper.Col_NoOfBags,
                            DatabaseHelper.Col_UniqueSwipeDetail,
                            DatabaseHelper.Col_DonorID,
                            DatabaseHelper.Col_SyncFlag
                    },
                    null, null, null, null, DatabaseHelper.Col_Id + " ASC", "1");
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String desc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_NoOfBags));
                String uniqueSwipe = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_UniqueSwipeDetail));
                String donorID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_DonorID));
                String flag = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_SyncFlag));
                result.put("Col_NoOfBags", desc);
                result.put("Col_UniqueSwipeDetail", uniqueSwipe);
                result.put("Col_DonorID", donorID);
                result.put("Col_SyncFlag", flag);
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public void updateBagDropDataSyncStatus(String swipe, String status, String bags) {
        try {
            database = databaseHelper.getWritableDatabase();
            //ContentValues values = new ContentValues();
            //values.put(DatabaseHelper.Col_SyncFlag, status);

            //database.update(DatabaseHelper.SYNC_BAGDROP_MASTER, values, DatabaseHelper.Col_UniqueSwipeDetail + "='" + swipe.toUpperCase() + "'", null);
            //database.update(DatabaseHelper.SYNC_BAGDROP_MASTER, values, DatabaseHelper.Col_Id+" = (SELECT MAX ("+DatabaseHelper.Col_Id+") FROM "+DatabaseHelper.SYNC_BAGDROP_MASTER+") AND "+DatabaseHelper.Col_UniqueSwipeDetail + "='" + swipe.toUpperCase() + "'", null);
            //UPDATE SyncBagDropMaster SET SyncFlag = 1 WHERE NoOfBags = 4 AND UniqueSwipeDetail = 'C001086401' and id = (SELECT MAX(id)  FROM SyncBagDropMaster
            //GROUP BY UniqueSwipeDetail HAVING UniqueSwipeDetail = 'C001086401');
            //UPDATE SyncBagDropMaster SET SyncFlag = 1 WHERE id = (SELECT MAX(id)  FROM SyncBagDropMaster
            //GROUP BY UniqueSwipeDetail, NoOfBags HAVING UniqueSwipeDetail = 'C001086401' AND NoOfBags=2);
            Cursor c = database.rawQuery("UPDATE " + DatabaseHelper.SYNC_BAGDROP_MASTER +
                                    " SET " + DatabaseHelper.Col_SyncFlag + "='" + status + "'" +
                                    " WHERE " + DatabaseHelper.Col_Id + "=(SELECT MAX("+DatabaseHelper.Col_Id+")" +
                                                                        " FROM "+DatabaseHelper.SYNC_BAGDROP_MASTER+
                                                                        " GROUP BY "+DatabaseHelper.Col_UniqueSwipeDetail+ ", "+DatabaseHelper.Col_NoOfBags+
                                                                        " HAVING "+DatabaseHelper.Col_UniqueSwipeDetail+" ='"+swipe.toUpperCase()+"' AND "+DatabaseHelper.Col_NoOfBags+" = "+bags+")", null);

            c.moveToFirst();
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public void updateBagDropDataSyncDonorID(String swipe, String donorID) {
        try {
            database = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.Col_DonorID, donorID);
            database.update(DatabaseHelper.SYNC_BAGDROP_MASTER, values, DatabaseHelper.Col_UniqueSwipeDetail + "='" + swipe.toUpperCase() + "'", null);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public void deleteBagDropDataByUniqueSwipeDetail(String swipe) {
        try {
            database = databaseHelper.getWritableDatabase();
            //database.delete(DatabaseHelper.SYNC_BAGDROP_MASTER, DatabaseHelper.Col_UniqueSwipeDetail + "='" + swipe.toUpperCase() + "'", null);
            database.delete(DatabaseHelper.SYNC_BAGDROP_MASTER, DatabaseHelper.Col_UniqueSwipeDetail + "='" + swipe.toUpperCase() + "' AND "+
                    DatabaseHelper.Col_SyncFlag + "=1", null);
            database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public String getDonorIdByUniqueSwipeDetail(String swipe) {
        String result = "";
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SYNC_BAGDROP_MASTER,
                    new String[]{DatabaseHelper.Col_DonorID},
                    DatabaseHelper.Col_UniqueSwipeDetail + "='" + swipe.toUpperCase() + "'",
                    null, null, null, null);
            if (cursor.moveToNext()) {
                String strDonorID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_DonorID));
                result = strDonorID;
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    /**
     * Delete Tables
     *
     * @param tableName
     */
    public void deleteTable(String tableName) {
        try {
            database = databaseHelper.getWritableDatabase();
            database.delete(tableName, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    /**
     * Get Setting Data from Setting Master using Setting ID
     *
     * @param settingName
     * @return
     */
    public HashMap<String, String> getSettingDataBySettingID(String settingName) {
        HashMap<String, String> result = new HashMap<String, String>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SETTING_MASTER, new String[]{DatabaseHelper.Col_TextSetting, DatabaseHelper.Col_IntSetting}, DatabaseHelper.Col_SettingCode + "=" + settingName, null, null, null, null);
            if (cursor.moveToNext()) {
                result.put("Text", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_TextSetting)));
                result.put("Number", cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_IntSetting)));
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    /**
     * Get All outlet name for Spinner
     *
     * @return
     */
    public ArrayList<String> getAllOutletName() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.OUTLET_MASTER, new String[]{DatabaseHelper.Col_OutletNo, DatabaseHelper.Col_OutletName}, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String outletNo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_OutletNo));
                String outleName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_OutletName));
                result.add("Outlet (" + outletNo + " - " + outleName + ")");
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public String getOutletNameFromOutletNo(String outletNo) {
        String result = "";
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.OUTLET_MASTER, new String[]{DatabaseHelper.Col_OutletNo, DatabaseHelper.Col_OutletName}, DatabaseHelper.Col_OutletNo + "=" + outletNo, null, null, null, null);
            if (cursor.moveToNext()) {
                String strOutletNo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_OutletNo));
                String strOutletName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_OutletName));
                result = "Outlet (" + strOutletNo + " - " + strOutletName + ")";
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    /**
     * Get All title from Title Master
     *
     * @return
     */
    public ArrayList<String> getAllTitle() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.TITLE_MASTER, new String[]{DatabaseHelper.Col_Title}, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Title));
                result.add(title);
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public ArrayList<String> getRagsType() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.RAGS_MASTER, new String[]{DatabaseHelper.Col_Description}, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String desc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Description));
                result.add(desc);
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public String getRagTypeIdFromRagsDesc(String ragsDesc) {
        String result = "";
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.rawQuery("SELECT TypeId, Description FROM RagsMaster WHERE Description='" + ragsDesc + "'", null);
            if (cursor.moveToNext()) {
                String strTypeId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_TypeId));
                result = strTypeId;
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public String getRagsDescFromRagTypeId(String ragTypeId) {
        String result = "";
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.rawQuery("SELECT TypeId, Description FROM RagsMaster WHERE TypeId='" + ragTypeId + "'", null);
            if (cursor.moveToNext()) {
                String strTypeId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Description));
                result = strTypeId;
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public ArrayList<String> getSourceDesc() {
        ArrayList<String> result = new ArrayList<>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SOURCE_MASTER, new String[]{DatabaseHelper.Col_Description}, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String sourceDesc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Description));
                result.add(sourceDesc);
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return result;
    }

    public String getSourceIdFromSourceDesc(String sourceDesc) {
        String result = "";
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.rawQuery("SELECT SourceID, Description FROM SourceMaster WHERE Description='" + sourceDesc + "'", null);
            if (cursor.moveToNext()) {
                String strTypeId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_SourceID));
                result = strTypeId;
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public String getSourceDescFromSourceId(String sourceId) {
        String result = "";
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.rawQuery("SELECT SourceID, Description FROM SourceMaster WHERE SourceID='" + sourceId + "'", null);
            if (cursor.moveToNext()) {
                String strTypeId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Description));
                result = strTypeId;
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public String getStockCodeDescFromStockCodeId(String stockCodeId) {
        String result = "";
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.rawQuery("SELECT HotKeyDesc FROM SSBHotKeyDetailMaster WHERE StockCodeID='" + stockCodeId + "'", null);
            if (cursor.moveToNext()) {
                String strStockCodeDesc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_HotKeyDesc));
                result = strStockCodeDesc;
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public ArrayList<String> getPriceBandFromStockCodeId(String stockCodeId) {
        ArrayList<String> result = new ArrayList<>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SSB_HOTKEY_TRANSC_MASTER, new String[]{DatabaseHelper.Col_StockCodeID, DatabaseHelper.Col_Price}, DatabaseHelper.Col_StockCodeID + "='" + stockCodeId + "'", null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Price));
                result.add(title);
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public void getHotKeyHeaderData() {
        ArrayList<HotKey> hotKeys = new ArrayList<>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SSB_HOTKEY_HEADER_MASTER, new String[]{DatabaseHelper.Col_HotKeyID, DatabaseHelper.Col_HotKeyDesc}, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String hotKeyId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_HotKeyID));
                String hotKeyDesc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_HotKeyDesc));
                Timber.v("ID: " + hotKeyId + " -- Desc: " + hotKeyDesc);
                hotKeys.add(new HotKey(hotKeyId, hotKeyDesc, GlobalApps.Constant.SelectionState.DESELECTED));
                cursor.moveToNext();
            }
            GlobalApps.getInstance().setArrayListHotKeyHeader(hotKeys);
            cursor.close();
            database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public void getHotKeyDetailData() {
        ArrayList<HotKeyDetails> hotKeyDetails = new ArrayList<>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SSB_HOTKEY_DETAIL_MASTER, new String[]{DatabaseHelper.Col_HotKeyID, DatabaseHelper.Col_StockCodeID, DatabaseHelper.Col_HotKeyDesc}, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String hotKeyId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_HotKeyID));
                String stockCodeId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_StockCodeID));
                String hotKeyDesc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_HotKeyDesc));
                Timber.v("ID: " + hotKeyId + " -- StockCode: " + stockCodeId + " -- Desc: " + hotKeyDesc);
                hotKeyDetails.add(new HotKeyDetails(hotKeyId, stockCodeId, hotKeyDesc));
                cursor.moveToNext();
            }
            GlobalApps.getInstance().setArrayListHotKeyDetails(hotKeyDetails);
            cursor.close();
            database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public void getHotKeyTransactionData() {
        ArrayList<HotKeyTransaction> hotKeyTransactions = new ArrayList<>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.SSB_HOTKEY_TRANSC_MASTER, new String[]{DatabaseHelper.Col_HotKeyID, DatabaseHelper.Col_StockCodeID, DatabaseHelper.Col_HotKeyDesc, DatabaseHelper.Col_Price}, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String hotKeyId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_HotKeyID));
                String stockCodeId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_StockCodeID));
                String hotKeyDesc = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_HotKeyDesc));
                String price = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_Price));
                Timber.v("ID: " + hotKeyId + " -- StockCode: " + stockCodeId + " -- Desc: " + hotKeyDesc + " -- Price: " + price);
                hotKeyTransactions.add(new HotKeyTransaction(hotKeyId, stockCodeId, hotKeyDesc, price, GlobalApps.Constant.Layout.NORMAL));
                cursor.moveToNext();
            }
            GlobalApps.getInstance().setArrayListHotKeyTransactions(hotKeyTransactions);
            cursor.close();
            database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }

    public ArrayList<HashMap<String, String>> getAllInterestData() {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.INTEREST_MASTER, new String[]{DatabaseHelper.Col_InterestID, DatabaseHelper.Col_InterestName}, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String interestID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_InterestID));
                String interestName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_InterestName));
                HashMap<String, String> data = new HashMap<>();
                data.put("interestID", interestID);
                data.put("interestName", interestName);
                result.add(data);
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public ArrayList<String> getAllInterestName() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            database = databaseHelper.getWritableDatabase();
            Cursor cursor = database.query(DatabaseHelper.INTEREST_MASTER, new String[]{DatabaseHelper.Col_InterestName}, null, null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String interestName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Col_InterestName));
                result.add(interestName);
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
        return null;
    }

    public void clear7DaysEarlierLog() {
        try {
            String earlierDate = GlobalApps.getInstance().getEarlierDateFromCurrentDate(-7);
            database = databaseHelper.getWritableDatabase();
            database.execSQL("DELETE FROM " + DatabaseHelper.LOG_MASTER + " WHERE " + DatabaseHelper.Col_LogDate + "< '" + earlierDate + "'");
            database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null && database.isOpen()) {
                database.close();
            }
        }
    }


}
