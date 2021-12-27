import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ComposeComponent } from './mail/compose/compose.component';
import { DraftComponent } from './mail/draft/draft.component';
import { FoldersComponent } from './mail/folders/folders.component';
import { InboxComponent } from './mail/inbox/inbox.component';
import { SentComponent } from './mail/sent/sent.component';
import { TrashComponent } from './mail/trash/trash.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MailComponent } from './mail/mail.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component:RegisterComponent},
  {path: 'mail', component: MailComponent,
   children: [
    {path: 'compose', component: ComposeComponent}, 
    {path: 'inbox', component: InboxComponent},
    {path: 'trash', component: TrashComponent},
    {path: 'sent', component: SentComponent},
    {path: 'draft', component: DraftComponent},
    {path: 'folders', component: FoldersComponent}
  ]},
  {path: '', redirectTo:'/login', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents =
 [ComposeComponent, InboxComponent, TrashComponent, SentComponent, DraftComponent, FoldersComponent, LoginComponent, RegisterComponent, MailComponent];