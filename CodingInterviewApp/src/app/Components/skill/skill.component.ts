import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Skill } from 'src/app/Interfaces/skill';
import { MainService } from 'src/app/main.service';

@Component({
  selector: 'app-skill',
  templateUrl: './skill.component.html',
  styleUrls: ['./skill.component.css']
})
export class SkillComponent implements OnInit {

  constructor(private service:MainService) {
  }

  skillList:Skill[] = [];
  skillById:Skill = {} as Skill;
  skillToBeUpdated:Skill = {} as Skill;

  ngOnInit(): void {
    this.getAllSkills();
  }

  getAllSkills(){
    this.service.getAllSkills().subscribe(res=>this.skillList = res);
  }
  getSkillById(id:number){
    this.service.getSkillById(id).subscribe(res=>this.skillById = res);
  }
  updateSkill(){
    this.service.updateSkill(this.skillToBeUpdated).subscribe(res=>{
      this.getAllSkills();
    });
  }
  deleteSkill(id:number){
    this.service.deleteSkill(id).subscribe(res=>{
      this.getAllSkills();
    });
  }

}
