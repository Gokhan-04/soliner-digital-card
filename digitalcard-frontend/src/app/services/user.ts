import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';

export interface Skill {
  id: number;
  name: string;
  level: number;
}

export interface Project {
  id: number;
  title: string;
  description: string;
  link: string;
}

export interface SocialLink {
  id: number;
  platform: string;
  url: string;
}

export interface UserResponse {
  id: number;
  name: string;
  email: string;
  avatarUrl: string;
  bio: string;
  skills: Skill[];
  projects: Project[];
  socialLinks: SocialLink[];
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  // Angular'ın yerleşik HTTP modülünü dahil ediyoruz
  private http = inject(HttpClient);

  // Backend ID'miz 5 olarak ayarlı
  private readonly baseUrl = 'http://localhost:8081/api/users/5';

  async getUser(): Promise<UserResponse> {
    return await firstValueFrom(this.http.get<UserResponse>(this.baseUrl));
  }

  async updateUser(payload: {
    name: string;
    email: string;
    avatarUrl: string;
    bio: string;
  }): Promise<UserResponse> {
    return await firstValueFrom(this.http.put<UserResponse>(this.baseUrl, payload));
  }

  async addSkill(payload: {
    name: string;
    level: number;
  }): Promise<Skill> {
    return await firstValueFrom(this.http.post<Skill>(`${this.baseUrl}/skills`, payload));
  }

  async addProject(payload: {
    title: string;
    description: string;
    link: string;
  }): Promise<Project> {
    return await firstValueFrom(this.http.post<Project>(`${this.baseUrl}/projects`, payload));
  }

  async addSocialLink(payload: {
    platform: string;
    url: string;
  }): Promise<SocialLink> {
    return await firstValueFrom(this.http.post<SocialLink>(`${this.baseUrl}/social-links`, payload));
  }

  async sendMessage(payload: {
    name: string;
    email: string;
    message: string;
  }): Promise<any> {
    return await firstValueFrom(this.http.post('http://localhost:8081/api/contact', payload));
  }
}
