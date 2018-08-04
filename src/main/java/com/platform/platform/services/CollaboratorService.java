package com.platform.platform.services;

import com.platform.platform.Models.Collaborator;
import com.platform.platform.Repositories.CollaboratorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class CollaboratorService {

  @Autowired
  private CollaboratorRepository collaboratorRepository;




  public void CommitCollaborator(Integer id , String name , String userName , String password)
  {
      Collaborator collaborator = new Collaborator();

      collaborator.setId(id);
      collaborator.setName(name);
      collaborator.setUserName(userName);
      collaborator.setPassword(password);


      collaboratorRepository.save(collaborator);
  }


    public List<Collaborator> FindAll()
    {
        List<Collaborator> collaboratorList = new ArrayList<>();

        collaboratorList.clear();
        collaboratorRepository.findAll().forEach(e ->  collaboratorList.add(e)); // find all Coll

        return collaboratorList ;
    }



    public Collaborator Exist(String username , String password)
    {
        List<Collaborator> collaboratorList = FindAll();


        for (Collaborator collaborator :  collaboratorList)
        {
            if(collaborator.getUserName().equals(username ) && collaborator.getPassword().equals( password))
                return collaborator;
        }

        return null;
    }

}
