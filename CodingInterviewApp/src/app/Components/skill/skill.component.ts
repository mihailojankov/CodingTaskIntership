import { Component, OnInit } from '@angular/core';
import { Skill } from 'src/app/Interfaces/skill';
import { MainService } from 'src/app/main.service';
import {FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-skill',
  templateUrl: './skill.component.html',
  styleUrls: ['./skill.component.css']
})
export class SkillComponent implements OnInit {

  constructor(private service: MainService, private formBuilder: FormBuilder) {
  }

  skillList: Skill[] = [];
  skillById: Skill = {} as Skill;
  addSkillForm = this.formBuilder.group({
    id: 0,
    name: ''
  }as Skill);

  ngOnInit(): void {
    this.getAllSkills();
  }

  getAllSkills(): void{
    this.service.getAllSkills().subscribe(res => this.skillList = res);
  }
  saveSkill(): void{
    this.service.saveSkill(this.addSkillForm.value).subscribe(() => {
      this.getAllSkills();
      this.addSkillForm.reset();
    });
  }

}
