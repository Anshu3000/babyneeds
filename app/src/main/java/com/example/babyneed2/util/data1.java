package com.example.babyneed2.util;

public class data1 {

    public  static  int data_version=1;
    public  static String  database_name="babyneed";
    public static  String table_name="item";
    public  static String  col_name_item="item";
    public  static  String  col_name_qty="qty";
    public  static  String col_name_color="color";
    public  static  String col_name_size="size";
    public static   String col_name_id="_ID";

    public static final  String create_table_item="CREATE TABLE "+table_name+" ( "+col_name_id+" INTEGER PRIMARY KEY ,"+col_name_item +" TEXT ," +
               col_name_color+" TEXT ,"+col_name_qty +" INTEGER ," +col_name_size+" INTEGER );";


    public static final String DROP_TABLE="DROP TABLE IF EXITS "+table_name;

}
