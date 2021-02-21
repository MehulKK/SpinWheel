package com.macv.achivement.utils;


import androidx.annotation.NonNull;


/**
 * Views which implement this interface change their color according to the given Bootstrap Brand
 */
public interface BootstrapBrandView {

    String KEY = "com.macv.api.view.BootstrapBrandView";

    /**
     * Changes the color of the view to match the given Bootstrap Brand
     *
     * @param bootstrapBrand the Bootstrap Brand
     */
    void setBootstrapBrand(@NonNull BootstrapBrand bootstrapBrand);

    /**
     * @return the current Bootstrap Brand
     */
    @NonNull BootstrapBrand getBootstrapBrand();

    void setProgressbarColor(BootstrapBrand bootstrapBrand, int color, int strokeColor, int backgroundColor);

}
