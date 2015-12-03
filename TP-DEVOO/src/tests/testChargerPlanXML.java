/**
 * 
 */
package tests;

import modele.Plan;

/**
 * @author nicolas
 *
 */
public class testChargerPlanXML {

	public static void main (String [] args){
		Plan plan = new Plan();
		plan.chargerPlan();
		System.out.println(plan);
	}
	
}
