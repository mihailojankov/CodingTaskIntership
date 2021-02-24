import { Component, OnInit } from '@angular/core';
import { JobCandidate } from 'src/app/Interfaces/job-candidate';
import { MainService } from 'src/app/main.service';

@Component({
  selector: 'app-job-candidate',
  templateUrl: './job-candidate.component.html',
  styleUrls: ['./job-candidate.component.css']
})
export class JobCandidateComponent implements OnInit {

  constructor(private service:MainService) { }

  jobCandidateList:JobCandidate[] = [];
  jobCandidateById:JobCandidate = {} as JobCandidate;
  jobCandidateToBeUpdated:JobCandidate = {} as JobCandidate;
  jobCandidateFullNameList:JobCandidate[] = [];

  ngOnInit(): void {
  }

  getAllCandidates(){
    this.service.getAllCandidates().subscribe(res=>this.jobCandidateList = res);
  }
  getCandidateById(id:number){
    this.service.getCandidateById(id).subscribe(res=>this.jobCandidateById = res);
  }
  updateCandidate(){
    this.service.updateCandidate(this.jobCandidateToBeUpdated).subscribe(res=>{
      this.getAllCandidates();
    });
  }
  deleteCandidate(id:number){
    this.service.deleteCandidate(id).subscribe(res=>{
      this.getAllCandidates();
    });
  }

}
