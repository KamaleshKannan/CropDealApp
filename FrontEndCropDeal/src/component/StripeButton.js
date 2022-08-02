import React from "react";
import StripeCheckout from "react-stripe-checkout";
import axios from "axios";

const StripeButton = ({ price,id }) => {
  const publishableKey = "pk_test_51Jg8ikSCkVGSmTrqgG2ASVz9qR0jWA0TiEzN2BgDedDvp3H9mgddqi578uZmDbfjzKAb3Qu0OehLrZTJxDhAVxlC00SPDFH36d";
  const stripePrice = price * 100;
const cartId=id;

  const onToken = (token) => {
    console.log(token);
    axios
      .post("http://localhost:8083/payment", {
        amount: stripePrice,
        token,
      })
      .then((response) => {
        console.log(response);
        UpdateOrder(cartId);
        alert("payment success");
      })
      .catch((error) => {
        console.log(error);
        alert("Payment failed");
      });
  };
  const UpdateOrder=(id)=>{
    axios
      .put("http://localhost:8054/apicart/Upadtecart"+"/"+id)
      .then((response) => {
        console.log(response);
       // alert(cartId);
        alert("payment success");
      })
      .catch((error) => {
        console.log(error);
        alert("Payment failed");
      });
  };

  return (
    <StripeCheckout
      amount={stripePrice}
      label="Pay Now"
      name="Wolf Elite"
      image="https://svgshare.com/i/CUz.svg"
      description={`Your total is ${price}`}
      panelLabel="Pay Now"
      token={onToken}
      stripeKey={publishableKey}
      currency="INR"
    />
  );
};

export default StripeButton;