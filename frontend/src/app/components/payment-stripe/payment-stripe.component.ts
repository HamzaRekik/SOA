import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { StripeService } from 'ngx-stripe';


@Component({
  selector: 'app-payment-stripe',
  templateUrl: './payment-stripe.component.html',
  styleUrls: ['./payment-stripe.component.css']
})
export class PaymentStripeComponent implements OnInit {
  @ViewChild('cardElement') cardElement: ElementRef | undefined;

  constructor(private stripeService: StripeService) { }

  ngOnInit() {
    this.loadStripe();
  }

  pay(amount: any) {
    const stripe = this.stripeService.stripe as any;

    const elements = stripe.elements();
    const card = elements.create('card');

    card.mount(this.cardElement!.nativeElement);

    stripe.confirmCardPayment('payment_intent_client_secret', {
      payment_method: {
        card: card,
      },
    }).then((result: any) => {
      if (result.error) {
        console.error(result.error.message);
      } else {
        console.log(result.paymentIntent);
        alert('Payment Success!!');
      }
    });
  }

  loadStripe() {
    this.stripeService.setKey('pk_test_51N1TkEDrbk7maP7MI4NuIary5VqPBXnuVg6oaVbxggM4EHUCqqQ2y0RGOCFiSgcxTHVoqtsu6x8VyOzkAikfa9CY00soOSzlO4');
  }
}
