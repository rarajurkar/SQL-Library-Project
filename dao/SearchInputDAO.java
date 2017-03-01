package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import to.SearchInputTO;
import to.SearchOutputTO;

public class SearchInputDAO {

	public List<SearchOutputTO> search(SearchInputTO sito) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inside search input dao");
		String url = "jdbc:mysql://localhost:3306/library";
		String username = "root";
		String password = "rucha771992";
		System.out.println("below forname..");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement stmt=conn.createStatement();
        String t=sito.getTitle();
       String a=sito.getAuthors();
       String b=sito.getIsbn();
       ResultSet rs;
       if("".equals(t) && "".equals(a))
       {
        String st=sito.getIsbn();
        String val= "select temp.isbn as isbn,temp.title as title, temp.Fullname as aname,temp.branch_id as branch_id, temp.Branch_name as branch_name,no_of_copies as total,no_of_copies-count(book_loans.isbn) as available from book_loans right join (select book.title,book.isbn,authors.Fullname,book_copies.branch_id,branch_name,no_of_copies from book_copies natural join book left join book_authors on book.isbn=book_authors.isbn inner join authors on authors.Author_id=book_authors.Author_id inner join library_branch on library_branch.Branch_id=book_copies.Branch_id where book.isbn like '%"+st+"%' group by branch_id) as temp on book_loans.isbn=temp.isbn and book_loans.branch_id=temp.branch_id group by temp.isbn,temp.branch_id,no_of_copies";
        rs=stmt.executeQuery(val);
       } 
       else if("".equals(b) && "".equals(a)){
           String tit=sito.getTitle();
           String val6= "select temp.isbn as isbn,temp.title as title,temp.Fullname as aname, temp.branch_id as branch_id, temp.Branch_name as branch_name,no_of_copies as total,no_of_copies-count(book_loans.isbn) as available from book_loans right join (select book.title,book.isbn,authors.Fullname,book_copies.branch_id,branch_name,no_of_copies from book_copies natural join book left join book_authors on book.isbn=book_authors.isbn inner join authors on authors.Author_id=book_authors.Author_id inner join library_branch on library_branch.Branch_id=book_copies.Branch_id where book.title like '%"+tit+"%' group by branch_id) as temp on book_loans.isbn=temp.isbn and book_loans.branch_id=temp.branch_id group by temp.isbn,temp.branch_id,no_of_copies";           
           rs=stmt.executeQuery(val6);
       }
       else if("".equals(b) && "".equals(t)){
           String auth=sito.getAuthors();//author.getText();
           String val7= "select temp.isbn as isbn,temp.title as title, temp.Fullname as aname,temp.branch_id as branch_id, temp.Branch_name as branch_name,no_of_copies as total,no_of_copies-count(book_loans.isbn) as available from book_loans right join (select book.title,book.isbn,authors.Fullname,book_copies.branch_id,branch_name,no_of_copies from book_copies natural join book left join book_authors on book.isbn=book_authors.isbn inner join authors on authors.Author_id=book_authors.Author_id inner join library_branch on library_branch.Branch_id=book_copies.Branch_id where Fullname like '%"+auth+"%' group by branch_id) as temp on book_loans.isbn=temp.isbn and book_loans.branch_id=temp.branch_id group by temp.isbn,temp.branch_id,no_of_copies";           rs=stmt.executeQuery(val7);     
       }
       else if("".equals(t)){
           String bk=sito.getIsbn();//bid.getText();
           String auth=sito.getAuthors();
           String val4="select temp.isbn as isbn,temp.title as title, temp.Fullname as aname,temp.branch_id as branch_id, temp.Branch_name as branch_name,no_of_copies as total,no_of_copies-count(book_loans.isbn) as available from book_loans right join (select book.title,book.isbn,authors.Fullname,book_copies.branch_id,branch_name,no_of_copies from book_copies natural join book left join book_authors on book.isbn=book_authors.isbn inner join authors on authors.Author_id=book_authors.Author_id inner join library_branch on library_branch.Branch_id=book_copies.Branch_id where Fullname like '%"+auth+"%' and book.isbn like '%"+bk+"%' group by branch_id) as temp on book_loans.isbn=temp.isbn and book_loans.branch_id=temp.branch_id group by temp.isbn,temp.branch_id,no_of_copies";
           rs=stmt.executeQuery(val4);
}
       else if("".equals(b)){
            String tit=sito.getTitle();//title.getText();
            String auth=sito.getAuthors();//author.getText();
            String val3="select temp.isbn as isbn,temp.title as title,temp.Fullname as aname, temp.branch_id as branch_id, temp.Branch_name as branch_name,no_of_copies as total,no_of_copies-count(book_loans.isbn) as available from book_loans right join (select book.title,book.isbn,authors.Fullname,book_copies.branch_id,branch_name,no_of_copies from book_copies natural join book left join book_authors on book.isbn=book_authors.isbn inner join authors on authors.Author_id=book_authors.Author_id inner join library_branch on library_branch.Branch_id=book_copies.Branch_id where Fullname like '%"+auth+"%'  and book.title like '%"+tit+"%' group by branch_id) as temp on book_loans.isbn=temp.isbn and book_loans.branch_id=temp.branch_id group by temp.isbn,temp.branch_id,no_of_copies";           rs=stmt.executeQuery(val3); 
       }
       else if("".equals(a)){
           String tit=sito.getTitle();//title.getText();
           String bk=sito.getIsbn();//bid.getText();
           String val2="select temp.isbn as isbn,temp.title as title,temp.Fullname as aname, temp.branch_id as branch_id, temp.Branch_name as branch_name,no_of_copies as total,no_of_copies-count(book_loans.isbn) as available from book_loans right join (select book.title,book.isbn,authors.Fullname,book_copies.branch_id,branch_name,no_of_copies from book_copies natural join book left join book_authors on book.isbn=book_authors.isbn inner join authors on authors.Author_id=book_authors.Author_id inner join library_branch on library_branch.Branch_id=book_copies.Branch_id where book.title like '%"+tit+"%'  and book.isbn like '%"+bk+"%' group by branch_id) as temp on book_loans.isbn=temp.isbn and book_loans.branch_id=temp.branch_id group by temp.isbn,temp.branch_id,no_of_copies";
           rs=stmt.executeQuery(val2);        
          }
         else
         {
           String tit=sito.getTitle();//title.getText();
           String auth=sito.getAuthors();//author.getText();
           String bk1=sito.getIsbn();//bid.getText();
           String val5="select temp.isbn as isbn,temp.title as title,temp.Fullname as aname, temp.branch_id as branch_id, temp.Branch_name as branch_name,no_of_copies as total,no_of_copies-count(book_loans.isbn) as available from book_loans right join (select book.title,book.isbn,authors.Fullname,book_copies.branch_id,branch_name,no_of_copies from book_copies natural join book left join book_authors on book.isbn=book_authors.isbn inner join authors on authors.Author_id=book_authors.Author_id inner join library_branch on library_branch.Branch_id=book_copies.Branch_id where Fullname like '%"+auth+"%'  and book.title like '%"+tit+"%'  and book.isbn like '%"+bk1+"%' group by branch_id) as temp on book_loans.isbn=temp.isbn and book_loans.branch_id=temp.branch_id group by temp.isbn,temp.branch_id,no_of_copies";
        		  // "select temp.isbn as isbn,temp.title as title, temp.branch_id as branch_id, no_of_copies as total,no_of_copies-count(book_loans.isbn) as available from book_loans right join (select book.title,book.isbn,branch_id,no_of_copies from book_copies natural join book left join book_authors on book.isbn=book_authors.isbn where author_name like '%"+auth+"%' and title like '%"+tit+"%' and book.isbn like '%"+bk1+"%' group by branch_id) as temp on book_loans.isbn=temp.isbn and book_loans.branch_id=temp.branch_id group by temp.isbn,temp.branch_id,no_of_copies";
           rs=stmt.executeQuery(val5);
    }
       List<SearchOutputTO> searchList = new ArrayList<SearchOutputTO>();
       while(rs.next()){
           SearchOutputTO soto= new  SearchOutputTO();
           soto.setBook_id(rs.getString("Isbn"));
           soto.setTitle(rs.getString("Title"));
           soto.setAname(rs.getString("aname"));
           soto.setBranch_id(rs.getString("Branch_Id"));
           soto.setBranch_name(rs.getString("Branch_name"));
           soto.setTotal(rs.getString("Total"));
           soto.setAvailable(rs.getString("Available"));  
           searchList.add(soto);
     }  
     conn.close();
     System.out.println(searchList);
	return searchList;  

	}
	

}
