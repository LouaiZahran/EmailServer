export class Email {
  private sender: string;
  private receiver: Array<string>;
  private subject: string;
  private body: string;
  private date: Date;
  private readStatus: boolean;
  private priority: number;

  constructor(sender: string, receiver: Array<string>, subject: string, body: string, priority: number, readStatus: boolean) {
    this.sender = sender;
    this.receiver = receiver;
    this.subject = subject;
    this.body = body;
    this.date = new Date();
    this.readStatus = readStatus;
    this.priority = priority;
  }

  static createEmailFromObject(obj: any){
    return new Email(obj.sender, obj.receiver, obj.subject, obj.body, obj.priority, obj.readStatus);
  }

  getSender(){
    return this.sender;
  }
  getReceiver(){
    return this.receiver;
  }
  getSubject(){
    return this.subject;
  }
  getBody(){
    return this.body;
  }
  getDate(){
    return this.date.getDate() + '/' + (this.date.getMonth() + 1 ) + '/' + this.date.getFullYear();
  }
  getPriority(){
    return this.priority;
  }
  getReadStatus(){
    return this.readStatus;
  }
}
