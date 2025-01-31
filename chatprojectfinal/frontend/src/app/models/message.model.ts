export type MessageType = {
    id?: number;
    senderId: number;
    receiverId?: number;
    content: string;
    timestamp?: string; 
    channelId?: number;
    senderName?: string;
  };