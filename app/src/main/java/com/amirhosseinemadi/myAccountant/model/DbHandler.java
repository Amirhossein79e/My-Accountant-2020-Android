package com.amirhosseinemadi.myAccountant.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.amirhosseinemadi.myAccountant.common.Application;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends DbCreate {

    private SQLiteDatabase db;

    public DbHandler() {
        super(Application.component.context());
    }

    public void def()
    {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("income",0);
        db.insert(TBL_NAME,null,cv);
        db.close();
    }


    public Money select()
    {
        db = getReadableDatabase();
        String query = "select * from "+TBL_NAME;
        Cursor cursor = db.rawQuery(query,null);
        Money money = new Money();
        while (cursor.moveToNext())
        {
            money.setIncome(cursor.getLong(cursor.getColumnIndex("income")));
        }
        cursor.close();
        db.close();
        return money;
    }


    public void addIncome(Money money)
    {
        Money m = select();
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("income",money.getIncome()+m.getIncome());
        db.update(TBL_NAME,cv,"id=1",null);
        db.close();
    }


    public void minIncome(Money money)
    {
        Money m = select();
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("income",m.getIncome()-money.getIncome());
        db.update(TBL_NAME,cv,"id=1",null);
        db.close();
    }


    public void addSpent(Spend spend)
    {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (spend.getBuy()!=null)
        {
            cv.put("buy",spend.getBuy());
        }
        if (spend.getCar()!=null)
        {
            cv.put("car",spend.getCar());
        }
        if (spend.getClothes()!=null)
        {
            cv.put("clothes",spend.getClothes());
        }
        if (spend.getFood()!=null)
        {
            cv.put("food",spend.getFood());
        }
        if (spend.getHealth()!=null)
        {
            cv.put("health",spend.getHealth());
        }
        if (spend.getHobby()!=null)
        {
            cv.put("hobby",spend.getHobby());
        }
        if (spend.getInternet()!=null)
        {
            cv.put("internet",spend.getInternet());
        }
        if (spend.getOther()!=null)
        {
            cv.put("other",spend.getOther());
        }
        if (spend.getPhone()!=null)
        {
            cv.put("phone",spend.getPhone());
        }
        if (spend.getRent()!=null)
        {
            cv.put("rent",spend.getRent());
        }
        if (spend.getTraffic()!=null)
        {
            cv.put("traffic",spend.getTraffic());
        }
        if (spend.getDetial()!=null)
        {
            cv.put("detial",spend.getDetial());
        }
        if (spend.getTime()!=null)
        {
            cv.put("time",spend.getTime());
        }
        if (spend.getPay()!=null)
        {
            cv.put("pay",spend.getPay());
        }
        db.insert(TBL_NAME2,null,cv);
        db.close();

    }

    
    public void addSpentMain(ContentValues cv)
    {
        db = getWritableDatabase();
        db.update(TBL_NAME,cv,"id = 1",null);
        db.close();
    }


    public List<Spend> selectDate(long start, long end, String key)
    {
        db = getReadableDatabase();
        String query = "select * from "+TBL_NAME2+" where time between "+start+" and "+end+ " and "+key+"!=0";
        Cursor cursor = db.rawQuery(query,null);
        List<Spend> list = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do
                {
                    Spend spend = new Spend();
                    spend.setTime(cursor.getLong(cursor.getColumnIndex("time")));
                    spend.setDetial(cursor.getString(cursor.getColumnIndex("detial")));

                    switch (key)
                    {
                        case "rent":
                            spend.setRent(cursor.getLong(cursor.getColumnIndex("rent")));
                            break;
                        case "health":
                            spend.setHealth(cursor.getLong(cursor.getColumnIndex("health")));
                            break;
                        case "food":
                            spend.setFood(cursor.getLong(cursor.getColumnIndex("food")));
                            break;
                        case "buy":
                            spend.setBuy(cursor.getLong(cursor.getColumnIndex("buy")));
                            break;
                        case "traffic":
                            spend.setTraffic(cursor.getLong(cursor.getColumnIndex("traffic")));
                            break;
                        case "clothes":
                            spend.setClothes(cursor.getLong(cursor.getColumnIndex("clothes")));
                            break;
                        case "phone":
                            spend.setPhone(cursor.getLong(cursor.getColumnIndex("phone")));
                            break;
                        case "car":
                            spend.setCar(cursor.getLong(cursor.getColumnIndex("car")));
                            break;
                        case "hobby":
                            spend.setHobby(cursor.getLong(cursor.getColumnIndex("hobby")));
                            break;
                        case "internet":
                            spend.setInternet(cursor.getLong(cursor.getColumnIndex("internet")));
                            break;
                        case "other":
                            spend.setOther(cursor.getLong(cursor.getColumnIndex("other")));
                            break;
                        case "pay":
                            spend.setPay(cursor.getLong(cursor.getColumnIndex("pay")));
                            break;
                    }
                    list.add(spend);

                } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }


    public List<Spend> selectNotDate(String key)
    {
        db = getReadableDatabase();
        String query = "select * from "+TBL_NAME2+" where "+key+"!=0";
        Cursor cursor = db.rawQuery(query,null);
        List<Spend> list = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do
            {
                Spend spend = new Spend();
                spend.setTime(cursor.getLong(cursor.getColumnIndex("time")));
                spend.setDetial(cursor.getString(cursor.getColumnIndex("detial")));

                switch (key)
                {
                    case "rent":
                        spend.setRent(cursor.getLong(cursor.getColumnIndex("rent")));
                        break;
                    case "health":
                        spend.setHealth(cursor.getLong(cursor.getColumnIndex("health")));
                        break;
                    case "food":
                        spend.setFood(cursor.getLong(cursor.getColumnIndex("food")));
                        break;
                    case "buy":
                        spend.setBuy(cursor.getLong(cursor.getColumnIndex("buy")));
                        break;
                    case "traffic":
                        spend.setTraffic(cursor.getLong(cursor.getColumnIndex("traffic")));
                        break;
                    case "clothes":
                        spend.setClothes(cursor.getLong(cursor.getColumnIndex("clothes")));
                        break;
                    case "phone":
                        spend.setPhone(cursor.getLong(cursor.getColumnIndex("phone")));
                        break;
                    case "car":
                        spend.setCar(cursor.getLong(cursor.getColumnIndex("car")));
                        break;
                    case "hobby":
                        spend.setHobby(cursor.getLong(cursor.getColumnIndex("hobby")));
                        break;
                    case "internet":
                        spend.setInternet(cursor.getLong(cursor.getColumnIndex("internet")));
                        break;
                    case "other":
                        spend.setOther(cursor.getLong(cursor.getColumnIndex("other")));
                        break;
                    case "pay":
                        spend.setPay(cursor.getLong(cursor.getColumnIndex("pay")));
                        break;
                }
                list.add(spend);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }


    public void insertChart(ChartModel chartModel)
    {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("time",chartModel.getTime());
        cv.put("income",chartModel.getIncome());
        db.insert(TBL_NAME3,null,cv);
        db.close();
    }


    public List<ChartModel> incomeList()
    {
        db = getReadableDatabase();
        String query = "select * from "+TBL_NAME3;
        List<ChartModel> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                ChartModel chartModel = new ChartModel();
                chartModel.setIncome(cursor.getLong(cursor.getColumnIndex("income")));
                chartModel.setTime(cursor.getLong(cursor.getColumnIndex("time")));
                list.add(chartModel);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }


    public void deleteChart()
    {
        db = getWritableDatabase();
        db.delete(TBL_NAME3,"rowid = 1",null);
        db.execSQL("VACUUM");
        db.close();
    }


    public Long selectNotDateAll(String key)
    {
        db = getReadableDatabase();
        String query = "select * from "+TBL_NAME2;
        Cursor cursor = db.rawQuery(query,null);
        List<Spend> list = new ArrayList<>();
        Long l = 0l;
        if (cursor.moveToFirst())
        {
            do
            {
                Spend spend = new Spend();
                spend.setTime(cursor.getLong(cursor.getColumnIndex("time")));
                spend.setDetial(cursor.getString(cursor.getColumnIndex("detial")));

                switch (key)
                {
                    case "rent":
                        spend.setRent(cursor.getLong(cursor.getColumnIndex("rent")));
                        l=l+spend.getRent();
                        break;
                    case "health":
                        spend.setHealth(cursor.getLong(cursor.getColumnIndex("health")));
                        l=l+spend.getHealth();
                        break;
                    case "food":
                        spend.setFood(cursor.getLong(cursor.getColumnIndex("food")));
                        l=l+spend.getFood();
                        break;
                    case "buy":
                        spend.setBuy(cursor.getLong(cursor.getColumnIndex("buy")));
                        l=l+spend.getBuy();
                        break;
                    case "traffic":
                        spend.setTraffic(cursor.getLong(cursor.getColumnIndex("traffic")));
                        l=l+spend.getTraffic();
                        break;
                    case "clothes":
                        spend.setClothes(cursor.getLong(cursor.getColumnIndex("clothes")));
                        l=l+spend.getClothes();
                        break;
                    case "phone":
                        spend.setPhone(cursor.getLong(cursor.getColumnIndex("phone")));
                        l=l+spend.getPhone();
                        break;
                    case "car":
                        spend.setCar(cursor.getLong(cursor.getColumnIndex("car")));
                        l=l+spend.getCar();
                        break;
                    case "hobby":
                        spend.setHobby(cursor.getLong(cursor.getColumnIndex("hobby")));
                        l=l+spend.getHobby();
                        break;
                    case "internet":
                        spend.setInternet(cursor.getLong(cursor.getColumnIndex("internet")));
                        l=l+spend.getInternet();
                        break;
                    case "other":
                        spend.setOther(cursor.getLong(cursor.getColumnIndex("other")));
                        l=l+spend.getOther();
                        break;
                    case "pay":
                        spend.setPay(cursor.getLong(cursor.getColumnIndex("pay")));
                        l=l+spend.getPay();
                        break;
                }
                list.add(spend);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return l;
    }


    public Long selectDateAll(Long start,Long end,String key)
    {
        db = getReadableDatabase();
        String query = "select * from "+TBL_NAME2+" where time between "+start+" and "+end;
        Cursor cursor = db.rawQuery(query,null);
        List<Spend> list = new ArrayList<>();
        Long l = 0l;
        if (cursor.moveToFirst())
        {
            do
            {
                Spend spend = new Spend();
                spend.setTime(cursor.getLong(cursor.getColumnIndex("time")));
                spend.setDetial(cursor.getString(cursor.getColumnIndex("detial")));

                switch (key)
                {
                    case "rent":
                        spend.setRent(cursor.getLong(cursor.getColumnIndex("rent")));
                        l=l+spend.getRent();
                        break;
                    case "health":
                        spend.setHealth(cursor.getLong(cursor.getColumnIndex("health")));
                        l=l+spend.getHealth();
                        break;
                    case "food":
                        spend.setFood(cursor.getLong(cursor.getColumnIndex("food")));
                        l=l+spend.getFood();
                        break;
                    case "buy":
                        spend.setBuy(cursor.getLong(cursor.getColumnIndex("buy")));
                        l=l+spend.getBuy();
                        break;
                    case "traffic":
                        spend.setTraffic(cursor.getLong(cursor.getColumnIndex("traffic")));
                        l=l+spend.getTraffic();
                        break;
                    case "clothes":
                        spend.setClothes(cursor.getLong(cursor.getColumnIndex("clothes")));
                        l=l+spend.getClothes();
                        break;
                    case "phone":
                        spend.setPhone(cursor.getLong(cursor.getColumnIndex("phone")));
                        l=l+spend.getPhone();
                        break;
                    case "car":
                        spend.setCar(cursor.getLong(cursor.getColumnIndex("car")));
                        l=l+spend.getCar();
                        break;
                    case "hobby":
                        spend.setHobby(cursor.getLong(cursor.getColumnIndex("hobby")));
                        l=l+spend.getHobby();
                        break;
                    case "internet":
                        spend.setInternet(cursor.getLong(cursor.getColumnIndex("internet")));
                        l=l+spend.getInternet();
                        break;
                    case "other":
                        spend.setOther(cursor.getLong(cursor.getColumnIndex("other")));
                        l=l+spend.getOther();
                        break;
                    case "pay":
                        spend.setPay(cursor.getLong(cursor.getColumnIndex("pay")));
                        l=l+spend.getPay();
                        break;
                }
                list.add(spend);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return l;
    }


    public void addCheque(Cheque cheque)
    {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",cheque.getName());
        cv.put("value",cheque.getValue());
        cv.put("date",cheque.getDate());
        cv.put("alarm",cheque.getAlarm());
        cv.put("account",cheque.getAccount());
        db.insert(TBL_NAME4,null,cv);
        db.close();
    }


    public List<Cheque> getCheque()
    {
        db = getReadableDatabase();
        String query = "select * from "+TBL_NAME4;
        Cursor cursor = db.rawQuery(query,null);
        List<Cheque> list = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do
                {
                   Cheque cheque = new Cheque();
                   cheque.setName(cursor.getString(cursor.getColumnIndex("name")));
                   cheque.setValue(cursor.getLong(cursor.getColumnIndex("value")));
                   cheque.setDate(cursor.getLong(cursor.getColumnIndex("date")));
                   cheque.setAlarm(cursor.getLong(cursor.getColumnIndex("alarm")));
                   cheque.setAccount(cursor.getString(cursor.getColumnIndex("account")));
                   list.add(cheque);
                }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }


}
