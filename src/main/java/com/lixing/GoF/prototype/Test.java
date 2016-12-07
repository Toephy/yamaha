package com.lixing.GoF.prototype;

public class Test {

	public static void main(String[] args) {
		try {
			String author = "Toephy";
			Article article = new Article(1, new Label("wuxia"), "Start", "hello world!", "Toephy");
			// true
			assert (article.getAuthor() == author);
			
			Article shallowArticle = (Article) article.clone();
			// true
			assert (article.getLabel() == shallowArticle.getLabel());
			// true
			assert (shallowArticle.getAuthor() == author);
			shallowArticle.getLabel().setLabelName("yanqing");
			// true
			assert(article.getLabel().getLabelName() == "yanqing");
			
			Article deepArticle = (Article) article.deepClone();
			// false
			assert (article.getAuthor() == deepArticle.getAuthor());
			// false
			assert (article.getLabel() == deepArticle.getLabel());
			// false
			assert (deepArticle.getAuthor() == author);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
