package com.tutorials.hp.sqlitefilterlistview.mDataBase;

/**
 * Created by Oclemmy on 5/2/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class Constants {
    //COLUMNS
    static final String ROW_ID="id";
    static final String NAME="name";

    //DB
    static final String DB_NAME="ii_DB";
    static final String TB_NAME="ii_TB";
    static final int DB_VERSION=1;

    //CREATE TB
    static final String CREATE_TB="CREATE TABLE ii_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL);";

    //DROP TB
    static final String DROP_TB="DROP TABLE IF EXISTS "+TB_NAME;


}
