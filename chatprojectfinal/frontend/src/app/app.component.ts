

import { Component } from '@angular/core';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { ChatComponent } from './components/chat/chat.component';
import { ApiService } from './services/api.service';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [SidebarComponent, ChatComponent],
  providers: [ApiService], 
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})


export class AppComponent {
  title = 'frontend';

  sharedChannelId : number | undefined;
  sharedFriendId : number | undefined;

  receiveChannelIdFromSidebar(id : number | undefined) {
    this.sharedChannelId = id
    console.log(id);
  }

  receiveFriendIdFromSidebar(id : number | undefined) {
    this.sharedFriendId = id
    console.log(id);
  }

}


