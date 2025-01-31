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
  public isCreateFormVisible = false;
  public isEditFormVisible = false;
  public selectedChannel: ChannelType | null = null;

  @Output() channelId : EventEmitter<number | undefined>  = new EventEmitter();
  @Output() friendId : EventEmitter<number | undefined>  = new EventEmitter();



  
  ngOnInit(): void {
      this.loadOwnerChannels();
      this.loadMemberChannels();
      this.loadFriends();
  } 

  public processOnCreate() {
    this.isCreateFormVisible = true;
  }
  
  async loadOwnerChannels() {
  const channels =  await this.apiService.getUserChannelsByOwner(this.userId).subscribe((result: any) => {
      this.channelsOwnerCollection = result.data;
      
    });
  }

  loadMemberChannels() {
    this.apiService.getUserChannelsByMember(this.userId).subscribe((result: any) => {
      this.channelsMemberCollection = result.data;
    });
  }

  async fetchUser($inputValue: number) {
    const response : any = await this.apiService.getUser($inputValue).toPromise();
       return response.data[0];
};

  sendChannelId($inputValue: number | undefined){
    this.channelId.emit($inputValue);
  }

  sendFriendId($inputValue: number | undefined){
    this.friendId.emit($inputValue);
  }

  addFriend($inputValue: number) {
   
      this.apiService
      .addFriend({
        userId: 1,
        friendId:2
      })
      .subscribe((result:any) => {
        this.loadFriends();
      });
    }

  loadFriends() {
    this.apiService.getUserFriends(this.userId).subscribe(async (result: any) => {
      for(var friend of result.data){
       this.friendsCollection.push(await this.fetchUser(friend.friendId));
      }

    });
  }

  public processOnCreateChannel($inputValue: string) {
    this.apiService
      .createChannel({
        name: $inputValue,
        ownerId: 1
      })
      .subscribe((result: any) => {
        this.loadOwnerChannels();
        this.isCreateFormVisible = false;

      });
  }

  public processOnSave() {
    this.apiService
      .updateChannel(this.selectedChannel!)
      .subscribe((result) => {
      });
      this.isEditFormVisible = false;
  }

  public processOnEdit($selectedElement: ChannelType) {
    this.isEditFormVisible = true;
    this.selectedChannel = $selectedElement;
  }

  public processOnDelete($selectedElement: ChannelType) {
    this.apiService
      .deleteChannel($selectedElement.id!)
      .subscribe((result: any) => {
        this.loadOwnerChannels();
      });
    }
  
}
