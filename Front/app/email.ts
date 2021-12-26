export class Email {
  private from: string;
  private to: string;
  private subject: string;
  private body: string;
  private date: Date;
  read: boolean;
  private priority: number;
  constructor(from: string, to: string, subject: string, body: string, priority: number) {
    this.from = from;
    this.to = to;
    this.subject = subject;
    this.body = body;
    this.date = new Date();
    this.read = false;
    this.priority = priority;
  }
  getFrom(){
    return this.from;
  }
  getTo(){
    return this.to;
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
}
