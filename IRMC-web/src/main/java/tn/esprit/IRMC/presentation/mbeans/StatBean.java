package tn.esprit.IRMC.presentation.mbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import tn.esprit.IRMC.services.ArticleServiceImpl;
import tn.esprit.IRMC.services.AuteurServiceImpl;
import tn.esprit.IRMC.persistence.*;
 
@ManagedBean
public class StatBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private BarChartModel animatedModel2;
    private PieChartModel pieModel1;
    @EJB
   	ArticleServiceImpl as;
   	@EJB
   	AuteurServiceImpl aus;
   	private List<Auteur> listAuteur = new ArrayList<Auteur>();
   	private List<Article> listarticle = new ArrayList<Article>();
    @PostConstruct
    public void init() {
        createAnimatedModels();
        createPieModels();        
    }
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    private void createPieModels() {
        createPieModel1();
    }
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         

        listarticle= as.getAllArticle();
      
      ChartSeries article = new ChartSeries();
      article.setLabel("Article");
      for (Article ar : listarticle) {
    	  pieModel1.set(ar.getPays(), as.countByCountry(ar.getPays()).size());
    	  
      }
        
        pieModel1.setTitle("Nos Article Selon le pays d'origine");
        pieModel1.setLegendPosition("w");
    }
 
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }
 
    private void createAnimatedModels() {

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Nos Article Dans le Temps");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        Axis   yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        
          listarticle= as.getAllArticle();
        
        ChartSeries article = new ChartSeries();
        article.setLabel("Article");
        for (Article ar : listarticle) {
        	
        Integer	y= ar.getDate().getYear()+1900;
        		article.set(y,as.findByYear(y).size());
        		System.out.println(y);
        		System.out.println(as.findByYear(y).size());
        	
        
        }
 
       
 
        model.addSeries(article);
      // model.addSeries(girls);
         
        return model;
    }
     

 
}
