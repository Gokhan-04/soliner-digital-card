import { Component, OnInit, ChangeDetectorRef, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserResponse, UserService } from '../../services/user';

@Component({
  selector: 'app-admin-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class AdminPage implements OnInit {
  private userService = inject(UserService);
  private cdr = inject(ChangeDetectorRef);

  user: UserResponse | null = null;
  loading = true;
  successMessage = '';
  errorMessage = '';

  userForm = { name: '', email: '', avatarUrl: '', bio: '' };
  skillForm = { name: '', level: 1 };
  projectForm = { title: '', description: '', link: '' };
  socialLinkForm = { platform: '', url: '' };

  async ngOnInit(): Promise<void> {
    await this.loadUser();
  }

  async loadUser(): Promise<void> {
    try {
      this.loading = true;
      this.errorMessage = '';
      const data = await this.userService.getUser();
      this.user = data;

      this.userForm = {
        name: data.name,
        email: data.email,
        avatarUrl: data.avatarUrl,
        bio: data.bio
      };
    } catch (error) {
      console.error('Kullanıcı verisi yüklenemedi:', error);
      this.errorMessage = 'Kullanıcı verisi yüklenemedi.';
    } finally {
      this.loading = false;
      this.cdr.detectChanges(); // Ekranı güncelleyen kritik satır
    }
  }

  async updateUser(name: string, email: string, avatarUrl: string, bio: string): Promise<void> {
    try {
      await this.userService.updateUser({ name, email, avatarUrl, bio });
      this.successMessage = 'Kullanıcı bilgileri güncellendi.';
      this.errorMessage = '';
      await this.loadUser();
    } catch (error) {
      console.error('Kullanıcı güncellenemedi:', error);
      this.errorMessage = 'Kullanıcı bilgileri güncellenemedi.';
      this.successMessage = '';
      this.cdr.detectChanges();
    }
  }

  async addSkill(name: string, level: string | number): Promise<void> {
    try {
      await this.userService.addSkill({ name, level: Number(level) });
      this.skillForm = { name: '', level: 1 };
      this.successMessage = 'Skill eklendi.';
      this.errorMessage = '';
      await this.loadUser();
    } catch (error) {
      console.error('Skill eklenemedi:', error);
      this.errorMessage = 'Skill eklenemedi.';
      this.successMessage = '';
      this.cdr.detectChanges();
    }
  }

  async addProject(title: string, description: string, link: string): Promise<void> {
    try {
      await this.userService.addProject({ title, description, link });
      this.projectForm = { title: '', description: '', link: '' };
      this.successMessage = 'Proje eklendi.';
      this.errorMessage = '';
      await this.loadUser();
    } catch (error) {
      console.error('Proje eklenemedi:', error);
      this.errorMessage = 'Proje eklenemedi.';
      this.successMessage = '';
      this.cdr.detectChanges();
    }
  }

  async addSocialLink(platform: string, url: string): Promise<void> {
    try {
      await this.userService.addSocialLink({ platform, url });
      this.socialLinkForm = { platform: '', url: '' };
      this.successMessage = 'Sosyal link eklendi.';
      this.errorMessage = '';
      await this.loadUser();
    } catch (error) {
      console.error('Sosyal link eklenemedi:', error);
      this.errorMessage = 'Sosyal link eklenemedi.';
      this.successMessage = '';
      this.cdr.detectChanges();
    }
  }
}
