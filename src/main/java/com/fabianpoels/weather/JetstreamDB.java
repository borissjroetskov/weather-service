package com.fabianpoels.weather;

import one.jetstream.JetstreamInstance;

public class JetstreamDB extends JetstreamInstance<RootData> {
    private static JetstreamDB instance;
 
    private JetstreamDB() {
    }
 
    public static JetstreamDB instance() {
        if (null == instance) {
            instance = new JetstreamDB();
        }
        return instance;
    }
 
    @Override
    protected RootData createDefaultRoot() {
        return super.createDefaultRoot();
    }
 
    @Override
    protected String createStorageDirectoryName() {
        return "weatherreport_storage";
    }
}