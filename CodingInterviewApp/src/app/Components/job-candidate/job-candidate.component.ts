import {Component, OnInit} from '@angular/core';
import { JobCandidate } from 'src/app/Interfaces/job-candidate';
import { MainService } from 'src/app/main.service';
import {FormBuilder} from '@angular/forms';
import {Skill} from '../../Interfaces/skill';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';


@Component({
  selector: 'app-job-candidate',
  templateUrl: './job-candidate.component.html',
  styleUrls: ['./job-candidate.component.css']
})
export class JobCandidateComponent implements OnInit {

  constructor(private service: MainService, private builder: FormBuilder) { }
  jobCandidateList: JobCandidate[] = [];
  skillList: Skill[] = [];
  skillListOrig: Skill[] = [];
  searchBySkillList: Skill[] = [];
  jobCandidateToBeUpdated: JobCandidate = {} as JobCandidate;
  skillListForUpdate: Skill[] = [];
  updatedSkills: Skill[] = [];
  jobCandidateFullNameList: JobCandidate[] = [];
  jobCandidateBySkillsList: JobCandidate[] = [];



  addCandidateForm = this.builder.group({
    id: 0,
    fullName: '',
    contactNumber: '',
    email: '',
    dateOfBirth: '',
    skillList: []
  }as JobCandidate);
  search = '';




  ngOnInit(): void {
    this.getAllCandidates();
    this.getAllSkills();
  }

  getAllCandidates(): void{
    this.service.getAllCandidates().subscribe(res => this.jobCandidateList = res);
  }

  getAllSkills(): void{
    this.service.getAllSkills().subscribe(res => {
      this.skillList = res;
      this.skillList.forEach(val => this.skillListOrig.push(Object.assign({}, val)));
    });
  }

  setCandidateForUpdate(candidate: JobCandidate): void{
    this.updatedSkills = [];
    this.jobCandidateToBeUpdated = candidate;

    if (candidate.skillList !== undefined){
      this.updatedSkills = candidate.skillList;
      this.skillListForUpdate = this.skillList.filter(o => !this.updatedSkills.find(o2 => o.id === o2.id));
    }
    else{
      this.skillListForUpdate = [];
      this.skillList.forEach(val => this.skillListForUpdate.push(Object.assign({}, val)));
    }

  }

  updateCandidate(): void{
    this.jobCandidateToBeUpdated.skillList = this.updatedSkills;
    this.service.updateCandidate(this.jobCandidateToBeUpdated).subscribe(() => {
      this.getAllCandidates();
    });
  }
  saveCandidate(): void{
    this.service.saveCandidate(this.addCandidateForm.value).subscribe((() => {
      this.getAllCandidates();
      this.addCandidateForm.reset();
    }));
  }

  deleteCandidate(id: number): void{
    this.service.deleteCandidate(id).subscribe(() => {
      this.getAllCandidates();
      this.jobCandidateFullNameList = [];
      this.jobCandidateBySkillsList = [];
    });
  }

  searchCandidateByFullName(): void{
    if (this.search.length > 5) {
      this.service.findCandidateByFullName(this.search).subscribe(res => this.jobCandidateFullNameList = res);
    }
  }
  clearList(): void{
    this.jobCandidateFullNameList = [];
  }

  searchCandidateBySkills(): void{
    console.log(this.searchBySkillList);
    this.service.findCandidateBySkills(this.searchBySkillList).subscribe(res => {
      this.jobCandidateBySkillsList = res;
    });
  }

  drop(event: CdkDragDrop<Skill[]>): void {
    this.jobCandidateBySkillsList = [];
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex);
    }
  }

}
