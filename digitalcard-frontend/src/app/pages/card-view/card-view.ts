import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserResponse, UserService } from '../../services/user';

@Component({
  selector: 'app-card-view',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './card-view.html',
  styleUrl: './card-view.css'
})
export class CardView implements OnInit {
  user: UserResponse | null = null;
  loading = true;
  error = '';

  contactForm = {
    name: '',
    email: '',
    message: ''
  };

  sendingMessage = false;
  successMessage = '';

  constructor(
    private userService: UserService,
    private cdr: ChangeDetectorRef
  ) {}

  async ngOnInit(): Promise<void> {
    console.log('1. CardView sayfası başlatıldı.');
    await this.loadUser();
  }

  async loadUser(): Promise<void> {
    try {
      this.loading = true;
      this.error = '';
      console.log('2. Backend\'den veri isteniyor...');

      this.user = await this.userService.getUser();
      console.log('3. Gelen kullanıcı verisi:', this.user);

    } catch (err) {
      console.error('HATA: Kullanıcı verisi çekilirken bir sorun oluştu:', err);
      this.error = 'Kullanıcı bilgileri backend\'den yüklenemedi.';
    } finally {
      this.loading = false;
      console.log('4. Yüklenme tamamlandı, ekran güncelleniyor.');
      // Angular'a verinin geldiğini ve HTML'i çizmesi gerektiğini zorla söylüyoruz:
      this.cdr.detectChanges();
    }
  }

  async sendMessage(): Promise<void> {
    if (!this.contactForm.name || !this.contactForm.email || !this.contactForm.message) {
      alert('Lütfen tüm alanları doldurun.');
      return;
    }

    try {
      this.sendingMessage = true;
      this.successMessage = '';

      await this.userService.sendMessage({
        name: this.contactForm.name,
        email: this.contactForm.email,
        message: this.contactForm.message
      });

      this.successMessage = 'Mesajınız başarıyla gönderildi.';
      this.contactForm = { name: '', email: '', message: '' };
    } catch (err) {
      console.error(err);
      alert('Mesaj gönderilirken bir hata oluştu.');
    } finally {
      this.sendingMessage = false;
      this.cdr.detectChanges();
    }
  }
}
