<ul>
			<li>
				<h2>Categories</h2>
				<ul>
								<?php
										
			
										$query="select * from category ";
			
										$res=mysqli_query($conn,$query);
											
										while($row=mysqli_fetch_assoc($res))
											{
												echo '<li><a href="subcat.php?cat='.$row['cat_id'].'&catnm='.$row["cat_nm"].'">'.$row["cat_nm"].'</a></li>';
												//pass catid not catnm
											}
			
											mysqli_close($conn);
								?>
				</ul>
			</li>
			
		</ul>