import {Component} from '@angular/core';
import {NgForm} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from 'src/app/services/auth.service';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {

    constructor(private service: AuthService, private router: Router) {
    }

    onSubmit(form: NgForm) {
        this.login(form)
    }

    login(form: NgForm) {
        this.service.login(form.value['email'], form.value['password']).subscribe(
            (response: any) => {
                if (response.id == null) {
                    alert("Adresse ou mot de passe est incorrecte")
                } else if (response.id == 3) {
                    localStorage.setItem('user_id', 'admin')
                    this.router.navigate(['/reglements']);
                } else {
                    localStorage.setItem('user_id', response.id)
                    this.router.navigate(['/historique']);
                }

            },
            (error) => {
                console.error(error);
            }
        );
    }

    protected readonly localStorage = localStorage;
}
