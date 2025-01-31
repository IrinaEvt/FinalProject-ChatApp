import { Component, Input, inject, SimpleChanges} from '@angular/core';
import { ApiService } from '../../services/api.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';



@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterOutlet],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent{
  private apiService = inject(ApiService);
  @Input() channelId!: number | undefined;
  @Input() friendId!: number | undefined;
  activeChat : string = "";
  messages: any[] = [];
  friendMessages: any[] = [];
  newMessage = '';



  ngOnInit(): void {
 
  }

  ngOnChanges(changes : SimpleChanges) : void {
      if(changes["channelId"]){
        this.loadChannelMessages();
      this.activeChat =  "channel"
      console.log("Heeeeeeeeeeeeeeey  CHANNEL ", this.channelId )}
      else if(changes["friendId"]){
        this.loadFriendMessages();
        this.activeChat = "friend";
        console.log("Heeeeeeeeeeeeeeey   ", this.friendId )
      }

 //   this.loadChannelMessages();
  }

  loadChannelMessages() {
    if (!this.channelId) return;
    this.apiService.getChannelMessages(this.channelId).subscribe(async(result: any) => {
      this.messages=[];
      for(var message of result.data){
        const user = await this.fetchUser(message.senderId)
        message.senderName = user.username
        this.messages.push(message);
       }    
       console.log(this.messages)
    });
  }


  async loadFriendMessages() {
      if (!this.friendId) return;
      
      var newMessages = []
      const response1 : any = await this.apiService.getFriendMessages(this.friendId, 1).toPromise()
      console.log(response1)

      for (var message of response1.data) {
          const user = await this.fetchUser(message.senderId)
          message.senderName = user.username
          newMessages.push(message)
      }
    
  
      const response2 : any = await this.apiService.getFriendMessages(1, this.friendId).toPromise()

      for (var message of response2.data) {
          const user = await this.fetchUser(message.senderId)
          message.senderName = user.username
          newMessages.push(message)
      }
  
      this.messages = newMessages;
  }



  sendMessage($inputValue: string) {
    if(this.activeChat == "channel"){
   
      this.apiService
      .sendChannelMessage({
        content: $inputValue,
        channelId: this.channelId,
        senderId: 1
      })
      .subscribe((result:any) => {
        console.log(result);
        this.loadChannelMessages();
      });
    }
      else{
        this.apiService
        .sendFriendMessage({
          content: $inputValue,
          receiverId: this.friendId,
          senderId: 1
        })
        .subscribe((result:any) => {
          console.log(result);
          this.loadFriendMessages();
        });
      }

  }

  async fetchUser($inputValue: number) {
    const response : any = await this.apiService.getUser($inputValue).toPromise();
    console.log(response);
       return response.data[0];
};
}