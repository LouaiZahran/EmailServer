import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ComposeComponent } from './compose/compose.component';
import { DraftComponent } from './draft/draft.component';
import { FoldersComponent } from './folders/folders.component';
import { InboxComponent } from './inbox/inbox.component';
import { SentComponent } from './sent/sent.component';
import { TrashComponent } from './trash/trash.component';

const routes: Routes = [
  {path: 'compose', component: ComposeComponent},
  {path: 'inbox', component: InboxComponent},
  {path: 'trash', component: TrashComponent},
  {path: 'sent', component: SentComponent},
  {path: 'draft', component: DraftComponent},
  {path: 'folders', component: FoldersComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [ComposeComponent, InboxComponent, TrashComponent, SentComponent, DraftComponent, FoldersComponent];