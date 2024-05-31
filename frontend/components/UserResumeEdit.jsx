import React from "react";
import Icon from "./Icon";
import { Textarea } from "./ui/textarea";
import {
  Select,
  SelectTrigger,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectValue,
} from "./ui/select";

import { cn } from "@/lib/utils";
import  useMediaQuery  from "@/hooks/use-media-query";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import {
  Drawer,
  DrawerClose,
  DrawerContent,
  DrawerDescription,
  DrawerFooter,
  DrawerHeader,
  DrawerTitle,
  DrawerTrigger,
} from "@/components/ui/drawer";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

export default function UserResumeEdit() {
  const [open, setOpen] = React.useState(false);
  const isDesktop = useMediaQuery("(min-width: 768px)");

  if (isDesktop) {
    return (
      <Dialog open={open} onOpenChange={setOpen}>
        <Button variant="outline" className="flex gap-2">
          <Icon name="pencil" size={14} /> Edit
        </Button>
        <DialogContent className="sm:max-w-[425px]">
          <DialogHeader>
            <DialogTitle>Edit profile</DialogTitle>
            <DialogDescription>
              Make changes to your profile here. Click save when you're done.
            </DialogDescription>
          </DialogHeader>
          <ProfileForm />
        </DialogContent>
      </Dialog>
    );
  }

  return (
    <Drawer open={open} onOpenChange={setOpen}>
      <Button variant="outline" className="flex gap-2">
        <Icon name="pencil" size={14} /> Edit
      </Button>
      <DrawerContent>
        <DrawerHeader className="text-left">
          <DrawerTitle>Edit profile</DrawerTitle>
          <DrawerDescription>
            Make changes to your profile here. Click save when you're done.
          </DrawerDescription>
        </DrawerHeader>
        <ProfileForm className="px-4" />
        <DrawerFooter className="pt-2">
          <DrawerClose asChild>
            <Button variant="outline">Cancel</Button>
          </DrawerClose>
        </DrawerFooter>
      </DrawerContent>
    </Drawer>
  );
}

// export default function UserResumeEdit() {
//   return (
//     <Sheet>
//       <SheetTrigger asChild>
//         <Button variant="outline" className="flex gap-2">
//           <Icon name="pencil" size={14} /> Edit
//         </Button>
//       </SheetTrigger>
//       <SheetContent className="overflow-y-scroll">
//         <SheetHeader>
//           <SheetTitle>Edit profile</SheetTitle>
//         </SheetHeader>
//         <ProfileForm />
//       </SheetContent>
//     </Sheet>
//   );
// }

function ProfileForm() {
  return (
    <form className="grid items-start gap-4">
      <div className="grid gap-2">
        <Label htmlFor="fullName">FullName</Label>
        <Input type="text" id="fullName" defaultValue="shadcn@example.com" />
      </div>
      <div className="grid gap-2">
        <Label htmlFor="username">Role</Label>
        <Input id="username" defaultValue="@shadcn" />
      </div>
      <div className="grid gap-2">
        <Label htmlFor="username">About</Label>
        <Textarea />
      </div>
      <div className="grid gap-2">
        <Label htmlFor="username">Gender</Label>
        <Select>
          <SelectTrigger className="">
            <SelectValue placeholder="Select your gender" />
          </SelectTrigger>
          <SelectContent>
            <SelectGroup>
              <SelectItem value="apple">Male</SelectItem>
              <SelectItem value="banana">Female</SelectItem>
            </SelectGroup>
          </SelectContent>
        </Select>
      </div>
      <div className="grid gap-2">
        <Label htmlFor="username">Location</Label>
        <Input id="username" defaultValue="@shadcn" />
      </div>
      <div className="grid gap-2">
        <Label htmlFor="username">Phone number</Label>
        <Input id="username" defaultValue="@shadcn" />
      </div>
      <div className="grid gap-2">
        <Label htmlFor="username">Portfolio</Label>
        <Input id="username" defaultValue="@shadcn" />
      </div>
      <div className="grid gap-2">
        <Label htmlFor="username">Facebook</Label>
        <Input id="username" defaultValue="@shadcn" />
      </div>
      <div className="grid gap-2">
        <Label htmlFor="username">LinkedIn</Label>
        <Input id="username" defaultValue="@shadcn" />
      </div>
      <div className="grid gap-2">
        <Label htmlFor="username">Twitter</Label>
        <Input id="username" defaultValue="@shadcn" />
      </div>
      <Button type="submit">Save changes</Button>
    </form>
  );
}
