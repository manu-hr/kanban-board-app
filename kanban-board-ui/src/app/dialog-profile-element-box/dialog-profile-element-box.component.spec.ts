import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogProfileElementBoxComponent } from './dialog-profile-element-box.component';

describe('DialogProfileElementBoxComponent', () => {
  let component: DialogProfileElementBoxComponent;
  let fixture: ComponentFixture<DialogProfileElementBoxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DialogProfileElementBoxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DialogProfileElementBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
