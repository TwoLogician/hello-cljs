(ns nightcoders.pallat1
    (:require [reagent.core :as r]))
  
  (def clicks (r/atom 0))
  (def color (r/atom "red"))
  (def names (r/atom ["Two" "Three" "Four"]))
  (def value (r/atom ""))
  (defn textArea [name]
    [:div
     [:p name]
     [:textarea {:value @value :on-change #(reset! value (-> % .-target .-value))}]
     [:div
      [:button {:on-click (fn []
                           (swap! names conj @value))}
       "Add"]]])
  (defn p [name]
    [:div
     [:p name]])
  
  (defn content []
    [:div
     [:p {:style {:color @color}} "You clicked " @clicks " times"]
     [:button {:on-click (fn []
                           (swap! clicks inc))}
      "Inc me"]
     [:button {:on-click (fn []
                           (swap! clicks dec))}
      "Dec me"]
     [:button {:on-click (fn []
                           (reset! color "green"))}
      "Color"]
     [:button {:on-click (fn []
                           (swap! names conj @value))}
      "Add New Name"]
     [:input {:type "text" :value @value :on-change #(reset! value (-> % .-target .-value))}]
     [textArea "TextArea"]
     (map (fn [n] [p n]) @names)
     [p "P"]])
     
  
  (r/render-component [content] (.querySelector js/document "#content"))
  