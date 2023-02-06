package com.joem.java;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class deliveryController {

    private double getVolume(parcelModel parcel) {
        return parcel.getHeight() * parcel.getLength() * parcel.getWidth();
    }

    private double calculateCost(parcelModel parcelModel) {

        double volume = getVolume(parcelModel);
        double cost = 0;

        if (parcelModel.getWeight() > 50) {
            cost = 0;
        } else if (parcelModel.getWeight() > 10 & parcelModel.getWeight() < 50) {
            cost = 20 * volume;
        } else if (volume < 1500) {
            cost = 0.03 * volume;
        } else if (volume > 1500 && volume < 2500) {
            cost = 0.04 * volume;
        }

        return cost;
    }

    private double getVoucherDiscount(@PathVariable String voucher) {
        String URI = "https://mynt-exam.mocklab.io/voucher/" + voucher + "?key=apikey";
        RestTemplate restTemplate = new RestTemplate();
        voucherModel vouch = restTemplate.getForObject(URI, voucherModel.class);

        return vouch.getDiscount();

    }

    @RequestMapping("/delivery")
    @ResponseBody
    private String delivery(Model model, @RequestBody parcelModel parcel) {

        double cost = calculateCost(parcel);
        double discount = getVoucherDiscount(parcel.getVoucher());

        if (parcel.getVoucher().isBlank() || parcel.getVoucher().isEmpty()) {
            return "The delivery cost for your parcel is PHP " + cost;
        } else {
            return "The delivery cost for your parcel is PHP " + cost + ". \n Voucher Code: " + parcel.getVoucher()
                    + ". \n Discount: " + discount + ". \n Total Discounted Cost: " + (cost - discount);
        }
    }

}
