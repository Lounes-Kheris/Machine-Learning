/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.terrier.matching.dsms;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.terrier.matching.MatchingQueryTerms;
import org.terrier.matching.OldBasicMatchingExtends;
//import static org.terrier.matching.OldBasicMatchingExtends.getMeta;
//import static org.terrier.matching.OldBasicMatchingExtends.getMeta;
import org.terrier.matching.ResultSet;
import org.terrier.structures.Index;
import org.terrier.structures.MetaIndex;

/**
 *
 * @author acer
 */
public class IntegrateStaticScore implements DocumentScoreModifier {
//populated one for each document in collection   
    //double prior[];

    public boolean modifyScores(Index I,
            MatchingQueryTerms mqt, ResultSet r) {
        double[] scores = r.getScores();
        int[] docids = r.getDocids();
       
        for (int i = 0; i < scores.length; i++) {
            try {
                
                String doc = OldBasicMatchingExtends.meta.getItem("docno", docids[i]);
                
                //System.out.println(doc);
                // System.out.println(OldBasicMatchingExtends.map.size());
                if (OldBasicMatchingExtends.map.containsKey(doc)){
               /* System.out.println("le document : "+i);
                System.out.println("le score initial est de : "+scores[i]);
                System.out.println("le score à priori est de : "+OldBasicMatchingExtends.map.get(doc)); */
                scores[i] = scores[i];//+2*OldBasicMatchingExtends.map.get(doc);
                
                }
                //System.out.println(OldBasicMatchingExtends.map.get(doc));
            } catch (IOException ex) {
                Logger.getLogger(IntegrateStaticScore.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
       return true;
    }

    @Override
    public String getName() {
        return ("Prior"); //To change body of generated methods, choose Tools | Templates.
    }

    public Object clone() {
        return this;
    }
}
