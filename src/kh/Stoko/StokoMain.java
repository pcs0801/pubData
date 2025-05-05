package kh.Stoko;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StokoMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		ArrayList<itemData> items = new ArrayList<>();
		ArrayList<itemData> cart = new ArrayList<>();
		FileLoad(items);

		boolean mainFlag = false;
		while (!mainFlag) {
			StokoMenu.menuDisplay();
			int modeSelect = menuSelect();

			switch (modeSelect) {
			case 1: {
				boolean adminFlag = false;
				while (!adminFlag) {
					StokoMenu.menuDisplay1();
					int adminMenu = menuSelect();

					switch (adminMenu) {
					case 1: {
						boolean stockInFlag = false;
						while (!stockInFlag) {
							StokoMenu.itemDisplay();
							int _num = menuSelect();
							if (_num == 15) {
								System.out.println("재고 입고 종료");
								break;
							}
							String itemName = "";
							int itemPrice = 0;
							switch (_num) {
							case 1:
								itemName = "과자";
								itemPrice = 1500;
								break;
							case 2:
								itemName = "아이스크림";
								itemPrice = 1000;
								break;
							case 3:
								itemName = "소스";
								itemPrice = 3000;
								break;
							case 4:
								itemName = "냉동식품";
								itemPrice = 5000;
								break;
							case 5:
								itemName = "탄산음료";
								itemPrice = 2000;
								break;
							case 6:
								itemName = "이온음료";
								itemPrice = 2000;
								break;
							case 7:
								itemName = "생수";
								itemPrice = 2000;
								break;
							case 8:
								itemName = "차";
								itemPrice = 1500;
								break;
							case 9:
								itemName = "소주";
								itemPrice = 2000;
								break;
							case 10:
								itemName = "맥주";
								itemPrice = 2500;
								break;
							case 11:
								itemName = "하이볼";
								itemPrice = 3000;
								break;
							case 12:
								itemName = "청소용품";
								itemPrice = 10000;
								break;
							case 13:
								itemName = "주방용품";
								itemPrice = 15000;
								break;
							case 14:
								itemName = "문구류";
								itemPrice = 1000;
								break;
							default:
								System.out.println("올바른 번호를 입력해주세요.");
								continue;
							}
							System.out.println("입고수량 : ");
							int stockInQty = Integer.parseInt(scan.nextLine());
							System.out.println("입고일자 : ");
							String stockInDate = scan.nextLine();
							itemData addItem = new itemData(itemName, itemPrice, stockInQty, stockInDate);
							items.add(addItem);
							saveFile(addItem);
							System.out.println("입고 완료");
						}
						break;
					}
					case 2: {
						boolean stockOutFlag = false;
						while (!stockOutFlag) {
							StokoMenu.itemDisplay();
							int _num = menuSelect();
							if (_num == 15) {
								System.out.println("재고 삭제 종료");
								break;
							}
							String itemName = "";
							switch (_num) {
							case 1:
								itemName = "과자";
								break;
							case 2:
								itemName = "아이스크림";
								break;
							case 3:
								itemName = "소스";
								break;
							case 4:
								itemName = "냉동식품";
								break;
							case 5:
								itemName = "탄산음료";
								break;
							case 6:
								itemName = "이온음료";
								break;
							case 7:
								itemName = "생수";
								break;
							case 8:
								itemName = "차";
								break;
							case 9:
								itemName = "소주";
								break;
							case 10:
								itemName = "맥주";
								break;
							case 11:
								itemName = "하이볼";
								break;
							case 12:
								itemName = "청소용품";
								break;
							case 13:
								itemName = "주방용품";
								break;
							case 14:
								itemName = "문구류";
								break;
							default:
								System.out.println("올바른 번호를 입력해주세요.");
								continue;
							}
							System.out.println("삭제할 수량 : ");
							int deleteQty = Integer.parseInt(scan.nextLine());
							boolean deleted = false;
							for (int i = 0; i < items.size(); i++) {
								itemData item = items.get(i);
								if (item.getName().equals(itemName)) {
									if (item.getQty() < deleteQty) {
										System.out.println("삭제 실패: 재고가 부족합니다.");
									} else if (item.getQty() == deleteQty) {
										items.remove(i);
										System.out.println("재고가 삭제되었습니다.");
									} else {
										item.setQty(item.getQty() - deleteQty);
										System.out.println("재고가 차감되었습니다.");
									}
									deleted = true;
									break;
								}
							}
							if (!deleted) {
								System.out.println("해당 상품이 존재하지 않습니다.");
							} else {
								saveAll(items);
							}
						}
						break;
					}
					case 3:
						System.out.println(items.toString());
						break;
					case 4:
						System.out.println("검색할 상품명 : ");
						String keyword = scan.nextLine();
						searchItem(items, keyword);
						break;
					case 5:
						System.out.println("입고일자 기준\n1. 오름차순\n2. 내림차순");
						int sortOrder = Integer.parseInt(scan.nextLine());
						sortByDate(items, sortOrder);
						for (itemData item : items) {
							System.out.println(item);
						}
						break;
					case 6:
						showSalesTotal();
						break;
					case 7:
						adminFlag = true;
						break;
					default:
						System.out.println("올바른 번호를 입력해주세요.");
					}
				}
				break;
			}
			case 2: {
				boolean userFlag = false;
				while (!userFlag) {
					StokoMenu.menuDisplay2();
					int userMenu = menuSelect();

					switch (userMenu) {
					case 1: {
						boolean cartFlag = false;

						while (!cartFlag) {
							StokoMenu.itemDisplay();
							int _num = menuSelect();

							if (_num == 15) {
								System.out.println("장바구니 담기 종료");
								break;
							}

							String itemName = "";
							int itemPrice = 0;

							switch (_num) {
							case 1:
								itemName = "과자";
								itemPrice = 1500;
								break;
							case 2:
								itemName = "아이스크림";
								itemPrice = 1000;
								break;
							case 3:
								itemName = "소스";
								itemPrice = 3000;
								break;
							case 4:
								itemName = "냉동식품";
								itemPrice = 5000;
								break;
							case 5:
								itemName = "탄산음료";
								itemPrice = 2000;
								break;
							case 6:
								itemName = "이온음료";
								itemPrice = 2000;
								break;
							case 7:
								itemName = "생수";
								itemPrice = 2000;
								break;
							case 8:
								itemName = "차";
								itemPrice = 1500;
								break;
							case 9:
								itemName = "소주";
								itemPrice = 2000;
								break;
							case 10:
								itemName = "맥주";
								itemPrice = 2500;
								break;
							case 11:
								itemName = "하이볼";
								itemPrice = 3000;
								break;
							case 12:
								itemName = "청소용품";
								itemPrice = 10000;
								break;
							case 13:
								itemName = "주방용품";
								itemPrice = 15000;
								break;
							case 14:
								itemName = "문구류";
								itemPrice = 1000;
								break;
							default:
								System.out.println("올바른 번호를 입력해주세요.");
								continue;
							}

							System.out.print("구매 수량을 입력하세요 : ");
							int buyQty = Integer.parseInt(scan.nextLine());

							int totalStock = 0;
							for (itemData item : items) {
								if (item.getName().equals(itemName)) {
									totalStock = totalStock + item.getQty();
								}
							}

							int currentCartQty = 0;
							for (itemData item : cart) {
								if (item.getName().equals(itemName)) {
									currentCartQty = currentCartQty + item.getQty();
								}
							}

							if (buyQty + currentCartQty > totalStock) {
								System.out.println("재고가 부족합니다. 현재 남은 수량: " + (totalStock - currentCartQty) + "개");
								continue;
							}

							boolean isExist = false;
							for (itemData item : cart) {
							    if (item.getName().equals(itemName)) {
							        item.setQty(item.getQty() + buyQty);
							        isExist = true;
							        break;
							    }
							}
							if (!isExist) {
							    itemData cartItem = new itemData(itemName, itemPrice, buyQty, "장바구니");
							    cart.add(cartItem);
							}
							System.out.println("장바구니에 추가되었습니다.");
						}
						break;
					}
					case 2: {
					    if (cart.isEmpty()) {
					        System.out.println("장바구니가 비어 있습니다.");
					        break;
					    }

					    System.out.println("현재 장바구니:");
					    for (int i = 0; i < cart.size(); i++) {
					        itemData item = cart.get(i);
					        System.out.printf("%d. [%s] %d개\n", i + 1, item.getName(), item.getQty());
					    }

					    System.out.print("삭제할 상품 번호를 입력하세요 (0: 취소): ");
					    int deleteIndex = Integer.parseInt(scan.nextLine());

					    if (deleteIndex == 0) {
					        System.out.println("삭제를 취소했습니다.");
					        break;
					    }

					    if (deleteIndex < 1 || deleteIndex > cart.size()) {
					        System.out.println("잘못된 번호입니다.");
					        break;
					    }

					    itemData removed = cart.remove(deleteIndex - 1);
					    System.out.printf("[%s] %d개가 장바구니에서 삭제되었습니다.\n", removed.getName(), removed.getQty());
					    break;
					}
					
					case 3:
						if (cart.isEmpty()) {
							System.out.println("장바구니가 비어있습니다.");
							break;
						}

						System.out.println("장바구니 내역:");
						int total = 0;
						for (itemData item : cart) {
							int cartTotal = item.getPrice() * item.getQty();
							System.out.printf("[%s] %d원 x %d개 = %d원\n", item.getName(), item.getPrice(),
									item.getQty(), cartTotal);
							total = total + cartTotal;
						}
						System.out.println("──────────────────────────────");
						System.out.printf("총 결제금액: %d원\n", total);

						System.out.print("결제하시겠습니까? (Y/N) : ");
						String confirm = scan.nextLine();

						if (confirm.equals("y") || confirm.equals("Y")) {
							saveReceipt(cart, total);
							for (itemData cartItem : cart) {
							    int remaining = cartItem.getQty();

							    items.sort((a, b) -> a.getInDate().compareTo(b.getInDate()));

							    for (itemData stockItem : items) {
							        if (!stockItem.getName().equals(cartItem.getName())) continue;

							        if (stockItem.getQty() >= remaining) {
							            stockItem.setQty(stockItem.getQty() - remaining);
							            break;
							        } else {
							            remaining = remaining - stockItem.getQty();
							            stockItem.setQty(0);
							        }
							    }
							}

							saveAll(items);
							System.out.println("결제가 완료되었습니다.");

							cart.clear();

						} else {
							System.out.println("결제가 취소되었습니다.");
						}
						break;
					case 4:
						printReceipt();
						break;

					case 5:
						userFlag = true;
						break;

					default:
						System.out.println("올바른 번호를 입력해주세요.");
					}
				}
				break;
			}
			case 3:
				System.out.println("프로그램을 종료합니다.");
				mainFlag = true;
				break;
			default:
				System.out.println("올바른 번호를 입력해주세요.");
			}
		}
	}

	public static int menuSelect() {
		boolean stopNumCheck = false;
		int num = 0;
		while (!stopNumCheck) {
			num = Integer.parseInt(scan.nextLine());
			boolean isNumCheck = Pattern.matches("^[0-9]{1,2}$", String.valueOf(num));
			if (isNumCheck == false || num == 0) {
				System.out.println("유효한 번호를 입력해주세요 : ");
				continue;
			}
			if (isNumCheck == true) {
				stopNumCheck = true;
			}
		}
		return num;
	}

	public static void FileLoad(ArrayList<itemData> items) {
		FileInputStream fi = null;
		try {
			fi = new FileInputStream("res/Stoko.txt");

			Scanner scan = new Scanner(fi);
			if (scan.hasNextLine())
				scan.nextLine();

			while (true) {
				if (!scan.hasNextLine()) {
					break;
				}
				String data = scan.nextLine();
				String[] tokens = data.split(",");
				String itemName = tokens[0];
				int itemPrice = Integer.parseInt(tokens[1]);
				int stockQty = Integer.parseInt(tokens[2]);
				String stockInDate = tokens[3];

				itemData itemList = new itemData(itemName, itemPrice, stockQty, stockInDate);
				items.add(itemList);
			}
			System.out.println("파일 불러오기 완료");
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("불러오기 오류가 발생하였습니다.");
		} finally {
			try {
				fi.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void saveFile(itemData item) {
		try {
			FileWriter fw = new FileWriter("res/Stoko.txt", true);

			String saveItem = item.getName() + "," + item.getPrice() + "," + item.getQty() + "," + item.getInDate() + "\n";

			fw.write(saveItem);
			fw.close();
		} catch (IOException e) {
			System.out.println("파일 저장 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}

	public static void searchItem(ArrayList<itemData> items, String keyword) {
		boolean found = false;

		System.out.printf("상품명\t 가격\t 수량\t 입고일자\t \n", "상품명", "가격", "수량", "입고일자");
		System.out.println("────────────────────────────────────");

		for (itemData item : items) {
			if (item.getName().equals(keyword)) {
				System.out.printf("%s\t %d\t %d\t %s\t\n", item.getName(), item.getPrice(), item.getQty(),
						item.getInDate());
				found = true;
			}
		}

		if (!found) {
			System.out.println("해당 상품이 존재하지 않습니다.");
		}
	}

	public static void saveAll(ArrayList<itemData> items) {
		try {
			FileWriter fw = new FileWriter("res/Stoko.txt");

			String firstLine = "상품명,가격,수량,입고일자\n";
			fw.write(firstLine);

			for (itemData item : items) {
				String line = item.getName() + "," + item.getPrice() + "," + item.getQty() + "," + item.getInDate() + "\n";
				fw.write(line);
			}

			fw.close();
		} catch (IOException e) {
			System.out.println("파일 저장 중 오류 발생");
			e.printStackTrace();
		}
	}

	public static void sortByDate(ArrayList<itemData> items, int order) {
		for (int i = 0; i < items.size() - 1; i++) {
			for (int j = i + 1; j < items.size(); j++) {
				String date1 = items.get(i).getInDate();
				String date2 = items.get(j).getInDate();

				boolean arrayFlag = false;

				if (order == 1 && date1.compareTo(date2) > 0) {
					arrayFlag = true;
				} else if (order == 2 && date1.compareTo(date2) < 0) {
					arrayFlag = true;
				}

				if (arrayFlag) {
					itemData temp = items.get(i);
					items.set(i, items.get(j));
					items.set(j, temp);
				}
			}
		}
	}

	public static void saveReceipt(ArrayList<itemData> cart, int total) {
		try {
			int receiptNo = 1;
			File file = new File("res/Receipt.txt");

			if (file.exists()) {
				Scanner scan = new Scanner(file);

				while (scan.hasNextLine()) {
					String line = scan.nextLine();

					if (line.indexOf("영수증번호") != -1) {
						String numStr = "";

						for (int i = 0; i < line.length(); i++) {
							char ch = line.charAt(i);
							if (ch >= '0' && ch <= '9') {
								numStr += ch;
							}
						}

						if (!numStr.equals("")) {
							int num = Integer.parseInt(numStr);

							if (num >= receiptNo) {
								receiptNo = num + 1;
							}
						}
					}
				}
				scan.close();
			}

			FileWriter fw = new FileWriter("res/Receipt.txt", true);

			fw.write("영수증번호: R" + receiptNo + "\n");
			fw.write("영수증\n");

			for (itemData item : cart) {
				String line = item.getName() + " - " + item.getPrice() + "원 x " + item.getQty() + "개 = " 
					+ (item.getPrice() * item.getQty()) + "원";
				fw.write(line + "\n");
			}

			fw.write("총 결제금액: " + total + "원\n");
			fw.write("결제일자: " + java.time.LocalDate.now() + "\n");
			fw.write("\n");

			fw.close();

		} catch (IOException e) {
			System.out.println("영수증 저장 중 오류가 발생했습니다.");
			e.printStackTrace();
		}
	}

	public static void printReceipt() {
	    try {
	        Scanner f = new Scanner(new File("res/Receipt.txt"));
	        System.out.println("[ 영수증 목록 ]");

	        while (f.hasNextLine()) {
	            String line = f.nextLine();
	            if (line.startsWith("영수증번호:")) {
	                System.out.print(line + " / ");
	            } else if (line.startsWith("총 결제금액:")) {
	                System.out.print(line + " / ");
	            } else if (line.startsWith("결제일자:")) {
	                System.out.println(line);
	            }
	        }

	        f.close();

	        System.out.print("\n번호 입력 (예: R1): ");
	        String input = scan.nextLine();

	        f = new Scanner(new File("res/Receipt.txt"));
	        boolean print = false;

	        while (f.hasNextLine()) {
	            String line = f.nextLine();
	            if (line.startsWith("영수증번호:")) {
	                print = line.contains(input);
	                if (print) {
	                    System.out.println("\n[ 영수증 ]");
	                    System.out.println("────────────────────");
	                    System.out.println(line);
	                }
	                continue;
	            }
	            if (print) {
	                if (line.isBlank()) break;
	                System.out.println(line);
	            }
	        }

	        f.close();
	    } catch (Exception e) {
	        System.out.println("오류 발생");
	    }
	}

	public static void showSalesTotal() {
		int total = 0;

		try {
			Scanner scan = new Scanner(new File("res/Receipt.txt"));

			while (scan.hasNextLine()) {
				String line = scan.nextLine();

				if (line.startsWith("총 결제금액: ")) {
					line = line.replace("총 결제금액: ", "");
					line = line.replace("원", "");
					total = total + Integer.parseInt(line.trim());
				}
			}

			scan.close();
			System.out.println("총 매출액: " + total + "원");

		} catch (Exception e) {
			System.out.println("매출 파일 읽기 오류가 발생했습니다.");
		}
	}
}