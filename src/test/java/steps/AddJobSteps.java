package steps;

import Utils.CommonMethods;
import Utils.DBUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AddJobSteps extends CommonMethods {

    String JTitleFrontEnd;
    String jDescription;
    String jNote;
    @Then("user clicks on the admin button")
    public void user_clicks_on_the_admin_button() {
      click(dashboardPage.adminButton);
    }
    @Then("user clicks on the job")
    public void user_clicks_on_the_job() {
     click(dashboardPage.adminJobButton);
    }
    @Then("user clicks on job title")
    public void user_clicks_on_job_title() {
       click(dashboardPage.adminJobJobTitleButton);
    }
    @Then("user clicks on add button")
    public void user_clicks_on_add_button() {
    click(jobPage.addButton);
    }
    @Then("user enters job {string} title")
    public void user_enters_job_title(String jobTitle) {
  sendText(jobTitle, jobPage.jobTitleF);
  JTitleFrontEnd =jobTitle;
    }
    @Then("user enters job description {string}")
    public void user_enters_job_description(String jobDescription) {
    sendText(jobDescription, jobPage.jobDescF);
    jDescription=jobDescription;
    }

    @Then("user enters job note {string}")
    public void user_enters_job_note(String jobNote) {
    sendText(jobNote, jobPage.jobNoteF);
     jNote=jobNote;
    }

    @Then("user clicks on the save button")
    public void user_clicks_on_the_save_button() {
     click(jobPage.jobSaveButton);
    }
    @Then("verify the data stored in data base priperly")
    public void verify_the_data_stored_in_data_base_priperly() {
        String query="select * from ohrm_job_title where job_title='"+ JTitleFrontEnd +"' and job_description='"+jDescription+"' and note='"+jNote+"';";
        List<Map<String,String>> data= DBUtils.fetch(query);
        Map<String,String> firstRow=data.get(0);
        String jobTitleBackEnd=firstRow.get("job_title");
        String jDescBE=firstRow.get("job_description");
        String jNoteBE=firstRow.get("note");

        Assert.assertEquals(JTitleFrontEnd,jobTitleBackEnd);
        Assert.assertEquals(jDescription,jDescBE);
        Assert.assertEquals(jNote,jNoteBE);

    }

}
