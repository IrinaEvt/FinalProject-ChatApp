import { Component, inject, Output, EventEmitter } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ChannelType } from '../../models/channel.model';
import { UserType } from '../../models/user.model';


@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterOutlet],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {
  private apiService = inject(ApiService);

  public userId = 1;
  public user : UserType | null = null;
  public channelsOwnerCollection: ChannelType[] = [];
  public channelsMemberCollection: ChannelType[] = [];
  public friendsCollection: UserType[] = [];
  @Output() channelId : EventEmitter<number | undefined>  = new EventEmitter();
  @Output() friendId : EventEmitter<number | undefined>  = new EventEmitter();



  
  ngOnInit(): void {
    console.log('Start sidebar component');
      this.loadOwnerChannels();
      this.loadMemberChannels();
      this.loadFriends();
  } 
  
  loadOwnerChannels() {
    this.apiService.getUserChannelsByOwner(this.userId).subscribe((result: any) => {
      console.log('ChannelsOwner:', result.data); 
      this.channelsOwnerCollection = result.data;
    });
  }

  loadMemberChannels() {
    this.apiService.getUserChannelsByMember(this.userId).subscribe((result: any) => {
      console.log('ChannelsMember:', result.data); 
      this.channelsMemberCollection = result.data;
    });
  }

  async fetchUser($inputValue: number) {
    const response : any = await this.apiService.getUser($inputValue).toPromise();
    console.log(response);
       return response.data[0];
};

  sendChannelId($inputValue: number | undefined){
    console.log($inputValue);
    this.channelId.emit($inputValue);
  }

  sendFriendId($inputValue: number | undefined){
    console.log($inputValue);
    this.friendId.emit($inputValue);
  }

  addFriend($inputValue: number) {
   
      this.apiService
      .addFriend({
        userId: 1,
        friendId:2
      })
      .subscribe((result:any) => {
        console.log(result);
        this.loadFriends();
      });
    }

  loadFriends() {
    this.apiService.getUserFriends(this.userId).subscribe(async (result: any) => {
      for(var friend of result.data){
       this.friendsCollection.push(await this.fetchUser(friend.friendId));
       console.log(friend);
      }

      console.log('Friends:', this.friendsCollection);
    });
  }
}
