/**
 * @author valkesh patel
 */
package com.quorg.model;

public class Store
{
    private String storeId,storeName;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Store(String storeId, String storeName) {
        this.storeId = storeId;
        this.storeName = storeName;
    }
}
