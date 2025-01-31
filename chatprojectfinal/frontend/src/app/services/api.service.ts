import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MessageType } from '../models/message.model';
import { FriendType } from '../models/friend.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private httpClient = inject(HttpClient);
  private apiUrl = 'http://localhost:1423'; 



  // Зареждане на канали
  getUserChannelsByOwner(userId: number){
    return this.httpClient.get(`${this.apiUrl}/channels/owner/${userId}`);
  }

  getUserChannelsByMember(userId: number){
    return this.httpClient.get(`${this.apiUrl}/channels/member/${userId}`);
  }


  // Зареждане на приятели
  getUserFriends(userId: number){
    return this.httpClient.get(`${this.apiUrl}/users/${userId}/friends`);
  }

  getUser(userId: number){
    return this.httpClient.get(`${this.apiUrl}/users/${userId}`)
  }

  // Зареждане на съобщения
  getChannelMessages(channelId: number){
    return this.httpClient.get(`${this.apiUrl}/messages/channel/${channelId}`);
  }

 // Изпращане на лично съобщение 
  getFriendMessages(friendId: number, userId: number){
   return this.httpClient.get(`${this.apiUrl}/messages/user/${userId}/friend/${friendId}`);
  }

  // Изпращане на съобщение
  sendChannelMessage(message: MessageType){
    return this.httpClient.post(`${this.apiUrl}/messages`, message);
  }

  addFriend(friend: FriendType){
    return this.httpClient.post(`${this.apiUrl}/users/friends`,friend);
  }

  sendFriendMessage(message: MessageType){
    return this.httpClient.post(`${this.apiUrl}/messages`, message);
  }
}
