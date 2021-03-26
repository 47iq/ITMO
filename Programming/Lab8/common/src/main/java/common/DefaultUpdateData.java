package common;

import java.io.Serializable;

public class DefaultUpdateData implements UpdateData, Serializable {
    private boolean nameSelected = false;
    private boolean priceSelected = false;
    private boolean discountSelected = false;
    private boolean typeSelected = false;
    private boolean refundableSelected = false;
    private boolean xSelected = false;
    private boolean ySelected = false;
    private boolean weightSelected = false;
    private boolean eyeColorSelected = false;
    private boolean hairColorSelected = false;
    private boolean countrySelected = false;

    public DefaultUpdateData() {
    }

    public DefaultUpdateData(boolean nameSelected, boolean priceSelected, boolean discountSelected, boolean typeSelected,
                             boolean refundableSelected, boolean xSelected, boolean ySelected, boolean weightSelected,
                             boolean eyeColorSelected, boolean hairColorSelected, boolean countrySelected) {
        this.nameSelected = nameSelected;
        this.priceSelected = priceSelected;
        this.discountSelected = discountSelected;
        this.typeSelected = typeSelected;
        this.refundableSelected = refundableSelected;
        this.xSelected = xSelected;
        this.ySelected = ySelected;
        this.weightSelected = weightSelected;
        this.eyeColorSelected = eyeColorSelected;
        this.hairColorSelected = hairColorSelected;
        this.countrySelected = countrySelected;
    }

    @Override
    public boolean isNameSelected() {
        return nameSelected;
    }

    @Override
    public boolean isPriceSelected() {
        return priceSelected;
    }

    @Override
    public boolean isDiscountSelected() {
        return discountSelected;
    }

    @Override
    public boolean isTypeSelected() {
        return typeSelected;
    }

    @Override
    public boolean isRefundableSelected() {
        return refundableSelected;
    }

    @Override
    public boolean isXSelected() {
        return xSelected;
    }

    @Override
    public boolean isYSelected() {
        return ySelected;
    }

    @Override
    public boolean isWeightSelected() {
        return weightSelected;
    }

    @Override
    public boolean isEyeColorSelected() {
        return eyeColorSelected;
    }

    @Override
    public boolean isHairColorSelected() {
        return hairColorSelected;
    }

    @Override
    public boolean isCountrySelected() {
        return countrySelected;
    }

    @Override
    public void setNameSelected() {
        this.nameSelected = true;
    }

    @Override
    public void setPriceSelected() {
        this.priceSelected = true;
    }

    @Override
    public void setDiscountSelected() {
        this.discountSelected = true;
    }

    @Override
    public void setTypeSelected() {
        this.typeSelected = true;
    }

    @Override
    public void setRefundableSelected() {
        this.refundableSelected = true;
    }

    @Override
    public void setXSelected() {
        this.xSelected = true;
    }

    @Override
    public void setYSelected() {
        this.ySelected = true;
    }

    @Override
    public void setWeightSelected() {
        this.weightSelected = true;
    }

    @Override
    public void setEyeColorSelected() {
        this.eyeColorSelected = true;
    }

    @Override
    public void setHairColorSelected() {
        this.hairColorSelected = true;
    }

    @Override
    public void setCountrySelected() {
        this.countrySelected = true;
    }
}
